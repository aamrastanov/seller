package az.tezapp.seller.server.controller.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import az.tezapp.seller.server.domain.Item;
import az.tezapp.seller.server.domain.ItemImage;
import az.tezapp.seller.server.domain.User;
import az.tezapp.seller.server.domain.repository.ItemImageRepository;
import az.tezapp.seller.server.domain.repository.ItemRepository;
import az.tezapp.seller.server.exception.ControllerLogicException;
import az.tezapp.seller.server.exception.ErrorCode;
import az.tezapp.seller.server.manager.ResourcesManager;

@RestController
@RequestMapping(value = "/items/{itemId}/images")
public class ItemImageController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemImageRepository itemImageRepository;

    @Autowired
    private ResourcesManager resourcesManager;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.POST)
    public ItemImage uploadItemImage(@PathVariable("itemId") Long itemId, @RequestParam("file") MultipartFile file,
            Authentication authentication) throws IOException, ControllerLogicException {
        Item item = itemRepository.findOne(itemId);
        if (item == null) {
            throw new ControllerLogicException(ErrorCode.ILLEGAL_FIELD, "Item access error");
        }
        User currentUser = (User) authentication.getPrincipal();
        if (!currentUser.equals(item.getUser())) {
            throw new ControllerLogicException(ErrorCode.ACCESS_DENIED, "Item access error");
        }
        if (!file.isEmpty()) {

            String fileUri = resourcesManager
                    .getWebUri(resourcesManager.generateUniqueFileName(itemId + "_", file.getOriginalFilename()));
            File recourcefile = new File(resourcesManager.getLocalResoucesPath() + fileUri);
            if (!recourcefile.exists()) {
                recourcefile.getParentFile().mkdirs();
                recourcefile.createNewFile();
            }
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(recourcefile));
            stream.write(bytes);
            stream.close();
            ItemImage itemImage = new ItemImage(fileUri, new Date(), item);
            itemImageRepository.save(itemImage);
            return itemImage;
        } else {
            throw new ControllerLogicException(ErrorCode.ILLEGAL_FIELD, "File is empty");
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{imageId}", method = RequestMethod.DELETE)
    public void deleteItemImage(@PathVariable("itemId") Long itemId, @PathVariable("imageId") Long imageId,
            Authentication authentication) throws ControllerLogicException {
        Item item = itemRepository.findOne(itemId);
        if (item == null) {
            throw new ControllerLogicException(ErrorCode.ILLEGAL_FIELD, "Item access error");
        }
        User currentUser = (User) authentication.getPrincipal();
        if (!currentUser.equals(item.getUser())) {
            throw new ControllerLogicException(ErrorCode.ACCESS_DENIED, "Item access error");
        }
        ItemImage itemImage = itemImageRepository.findOne(imageId);
        if (itemImage == null) {
            throw new ControllerLogicException(ErrorCode.ILLEGAL_FIELD, "ItemImage access error");
        }
        File file = new File(resourcesManager.getLocalResoucesPath() + itemImage.getFileUri());
        itemImageRepository.delete(itemImage);
        if (file.exists()) {
            file.delete();
        }
    }
}

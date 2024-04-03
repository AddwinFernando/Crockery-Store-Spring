package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Item;
import com.restapi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class DownloadService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    StorageService storageService;
    public File getFile(Long id) throws IOException {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));

        Resource resource = storageService.loadFileAsResource(item.getPhoto());

        return resource.getFile();
    }
}

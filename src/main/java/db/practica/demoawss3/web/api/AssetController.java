package db.practica.demoawss3.web.api;

import db.practica.demoawss3.Models.vm.Asset;
import db.practica.demoawss3.Service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/assets/")
public class AssetController {

    @Autowired
    private S3Service s3Service;

    @PostMapping("uploads")
    Map<String, String> upload(@RequestParam MultipartFile file){
        String key = s3Service.putObject(file);

        Map<String,String> resutl = new HashMap<>();
        resutl.put("key", key);
        resutl.put("url", s3Service.getObjetcUrl(key));

        return resutl;
    }

    @GetMapping(value = "get-objetc", params = "key")
    ResponseEntity<ByteArrayResource> getObject(@RequestParam String key){
        Asset asset = s3Service.getObjetc(key);
        ByteArrayResource resource = new ByteArrayResource(asset.getContent());

        return ResponseEntity
                .ok()
                .header("content-type", asset.getContentType())
                .contentLength(asset.getContent().length)
                .body(resource);
    }

    @DeleteMapping(value = "delete-objetc", params = "key")
    void deleteObject(@RequestParam String key){
        s3Service.deleteObject(key);
    }
}

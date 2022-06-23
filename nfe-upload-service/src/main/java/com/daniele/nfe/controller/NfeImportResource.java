package com.daniele.nfe.controller;

import com.daniele.nfe.domain.Nfe;
import com.daniele.nfe.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@AllArgsConstructor
@RestController
public class NfeImportResource extends RootController{

    private final StorageService storageService;

    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(description = "Import a NF-e document")
    @PostMapping(value = "/import" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Nfe> handleFileUpload(@RequestParam("file") MultipartFile file) {

        Nfe data = storageService.store(file);

        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                        .path("/{id}")
                        .buildAndExpand(data.getId())
                        .toUri();
        return ResponseEntity.created(uri).header("Access-Control-Allow-Headers","*").body(data);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping( produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Search All Nfes")
    public ResponseEntity<List<Nfe>> getAllNfe( ) {
        return ResponseEntity.ok(storageService.findAll());
    }

}

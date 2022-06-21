package com.daniele.nfe.controller;

import com.daniele.nfe.domain.Nfe;
import com.daniele.nfe.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.ws.rs.BadRequestException;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

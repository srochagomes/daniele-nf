package com.daniele.nfe.controller;

import com.daniele.nfe.domain.NotaFiscal;
import com.daniele.nfe.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
public class NfeDeletaResource extends RootController{

    private final StorageService storageService;

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Delete NFe by id")
    public ResponseEntity<List<NotaFiscal>> getDeleteByIDNfe(@PathVariable("id") @Parameter(description = "NFe number identify") Long id ) {
        storageService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

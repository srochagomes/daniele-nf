package com.daniele.nfe.service;

import com.daniele.nfe.domain.Nfe;
import com.daniele.nfe.domain.NfeStatus;
import com.daniele.nfe.infrastructure.entity.NfeEntity;
import com.daniele.nfe.infrastructure.repossitory.NfeRepository;
import com.daniele.nfe.parsers.NfeParser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageService {

    @NonNull
    private NfeRepository repository;

    @Value(value = "${path.input}")
    private String pathInput;

    public Nfe store(MultipartFile file) {
        if (!file.isEmpty()) {
            var directoryInput = new File(this.pathInput);

            if (!directoryInput.exists()) {
                directoryInput.mkdir();
            }
            String orgName = file.getOriginalFilename();

            NfeEntity nfe = NfeEntity.builder()
                    .status(NfeStatus.AGUARDANDO_PROCESSAMENTO)
                    .dhRegistro(LocalDateTime.now())
                    .fileNameOriginal(orgName)
                    .build();

            nfe = repository.save(nfe);

            String filePath = this.pathInput + nfe.nameFileProcess();
            File dest = new File(filePath);
            try {
                file.transferTo(dest.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return Nfe.builder().id(1l).build();
    }

    public List<Nfe> findAll() {
        return repository.findAllByOrderByDhRegistroDesc()
                .stream().map(NfeParser::parser).collect(Collectors.toList());
    }
}

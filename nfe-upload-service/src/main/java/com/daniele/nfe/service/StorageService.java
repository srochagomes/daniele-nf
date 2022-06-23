package com.daniele.nfe.service;

import com.daniele.nfe.domain.NotaFiscal;
import com.daniele.nfe.domain.NotaFiscalDuplicata;
import com.daniele.nfe.domain.NotaFiscalStatus;
import com.daniele.nfe.infrastructure.entity.NfeDuplicataEntity;
import com.daniele.nfe.infrastructure.entity.NfeEntity;
import com.daniele.nfe.infrastructure.repossitory.NfeRepository;
import com.daniele.nfe.parsers.NfeParser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.NotFoundException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageService {

    @NonNull
    private NfeRepository repository;

    @Value(value = "${path.input}")
    private String pathInput;

    public NotaFiscal store(MultipartFile file) {
        if (!file.isEmpty()) {
            var directoryInput = new File(this.pathInput);

            if (!directoryInput.exists()) {
                directoryInput.mkdir();
            }
            String orgName = file.getOriginalFilename();

            NfeEntity nfe = NfeEntity.builder()
                    .status(NotaFiscalStatus.AGUARDANDO_PROCESSAMENTO)
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
        return NotaFiscal.builder().id(1l).build();
    }

    public List<NotaFiscal> findAll() {
        return repository.findAllByOrderByDhRegistroDesc()
                .stream().map(NfeParser::parser).collect(Collectors.toList());
    }

    @Transactional
    public void fileUploadFail(NotaFiscal notaFiscal){
        if (ObjectUtils.allNotNull(notaFiscal,notaFiscal.getId())){
            Optional<NfeEntity> notaEncontrada = repository.findById(notaFiscal.getId());
            if (notaEncontrada.isPresent()){
                notaEncontrada.get().setStatus(NotaFiscalStatus.PROCESSADA_COM_ERRO);
            }
        }
    }

    @Transactional
    public void fileUploadSuccess(NotaFiscal notaFiscal){
        if (ObjectUtils.allNotNull(notaFiscal,notaFiscal.getId())){
            Optional<NfeEntity> notaEncontrada = repository.findById(notaFiscal.getId());
            if (notaEncontrada.isPresent()){
                NfeEntity nfe = notaEncontrada.get();
                nfe.setStatus(NotaFiscalStatus.PROCESSADA);
                nfe.setDhRegistro(notaFiscal.getDhRegistro());
                nfe.setNomeDestinatario(notaFiscal.getNomeDestinatario());
                nfe.setNomeEmitente(notaFiscal.getNomeEmitente());
                nfe.setNumero(notaFiscal.getNumero());
                nfe.setValorNotaFiscal(notaFiscal.getValorNotaFiscal());

                if (ObjectUtils.allNotNull(notaFiscal.getDuplicata())){
                    NotaFiscalDuplicata duplicata = notaFiscal.getDuplicata();
                    NfeDuplicataEntity nfeDuplicata =
                        Optional.ofNullable(nfe.getDuplicata()).orElseGet(()->NfeDuplicataEntity
                                .builder()
                                .id(nfe.getId()).build());



                    nfeDuplicata.setDtVencto(duplicata.getDtVencto());
                    nfeDuplicata.setNumero(duplicata.getNumero());
                    nfeDuplicata.setValor(duplicata.getValor());
                    nfeDuplicata.setNfe(nfe);
                    nfe.setDuplicata(nfeDuplicata);
                }
            }
        }
    }

    @Transactional
    public void delete(Long id){

        NfeEntity notaEncontrada = repository.findById(id).orElseThrow(NotFoundException::new);

        repository.delete(notaEncontrada);

    }

}

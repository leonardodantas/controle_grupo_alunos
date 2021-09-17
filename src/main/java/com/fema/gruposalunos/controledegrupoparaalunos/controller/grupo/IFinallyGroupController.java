package com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.net.HttpURLConnection;

public interface IFinallyGroupController {

    @PutMapping(value = "/finally/{id}", name = "Fechar grupo pelo ID")
    @ApiOperation(value = "Fechar grupo pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    ResponseEntity<?> finallyGroup(@PathVariable String id);

}
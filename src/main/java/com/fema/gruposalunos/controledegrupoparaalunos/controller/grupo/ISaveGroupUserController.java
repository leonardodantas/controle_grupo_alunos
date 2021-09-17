package com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.request.GroupUserRequestDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.net.HttpURLConnection;

public interface ISaveGroupUserController {

    @PostMapping(name = "Insere um usuario no grupo")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insere um usuario no grupo")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Returns", response = GroupUserRequestDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    ResponseEntity<GroupUserRequestDTO> insertUserInGroup(@RequestBody @Valid GroupUserRequestDTO request);

}

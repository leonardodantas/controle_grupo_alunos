package com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response.UserGroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.HttpURLConnection;

public interface IFindGroupController {

    @GetMapping(value = "/all", name = "Retorna todos os grupos paginados")
    @ApiOperation(value = "Recupera todos os Grupos")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = GroupResponseDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    ResponseEntity<GroupResponseDTO> findAll(Pageable pageable);

    @GetMapping(value = "/all/participants" ,name = "Retorna todos os grupos paginados e com seus integrantes")

    @ApiOperation(value = "Recupera todos os Grupos com participantes")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = UserGroupResponseDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    ResponseEntity<UserGroupResponseDTO> findAllWithParticipants(Pageable pageable);

    @GetMapping(value = "/{id}", name = "Retorna um grupo pelo id")
    @ApiOperation(value = "Retorna um grupo pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = GroupResponseDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    ResponseEntity<GroupResponseDTO> findById(@PathVariable String id);
}

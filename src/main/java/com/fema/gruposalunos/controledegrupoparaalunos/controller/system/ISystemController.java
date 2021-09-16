package com.fema.gruposalunos.controledegrupoparaalunos.controller.system;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.HttpURLConnection;

public interface ISystemController {

    @PostMapping(value = "/finally", name = "Finalizar todo o sistema")
    @ApiOperation(value = "Finalizar todo o sistema")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    ResponseEntity<?> finishSystem();

}

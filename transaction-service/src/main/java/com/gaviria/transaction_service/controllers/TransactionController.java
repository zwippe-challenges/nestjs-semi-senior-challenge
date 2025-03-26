package com.gaviria.transaction_service.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaviria.transaction_service.dto.TransactionRequest;
import com.gaviria.transaction_service.dto.TransactionResponse;
import com.gaviria.transaction_service.services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions", description = "Endpoint para gestionar las transacciones")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @Operation(summary = "Crear una nueva transacción", description = """
                Crea una nueva transacción en el sistema. Se requiere un objeto `TransactionRequest` con los detalles
                de la transacción. El endpoint devuelve los datos de la transacción creada.
            """, responses = {
            @ApiResponse(responseCode = "200", description = "Transacción creada con éxito", content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<TransactionResponse> createTransaction(
            @Parameter(description = "Datos de la transacción a crear", required = true) @RequestBody TransactionRequest transaction) {
        return ResponseEntity.ok(transactionService.createTransaction(transaction));
    }

    @GetMapping
    @Operation(summary = "Obtener todas las transacciones con paginación", description = """
                Recupera una lista paginada de transacciones. Se puede utilizar `page`, `size` y `sort` en los
                parámetros de la solicitud para manejar la paginación y el ordenamiento.
            """, responses = {
            @ApiResponse(responseCode = "200", description = "Lista paginada de transacciones", content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Page<TransactionResponse>> getAllTransactions(
            @Parameter(description = "Número de página (por defecto 0)") @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Cantidad de elementos por página (por defecto 10)") @RequestParam(defaultValue = "10") int size,

            @Parameter(description = "Criterios de ordenamiento, ej: transactionAmount") @RequestParam(defaultValue = "transactionAmount") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.split(",")));
        return ResponseEntity.ok(transactionService.getAllTransactions(pageable));
    }
}

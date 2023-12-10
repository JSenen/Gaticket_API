package com.juansenen.gaticket.controllers;

import com.juansenen.gaticket.domain.Incidences;
import com.juansenen.gaticket.domain.IncidencesHistory;
import com.juansenen.gaticket.service.IncidencesHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "incidenceHistory", description = "This controller contains all hisotry of incidences fix it")
public class IncidenceHistoryController {

    private final static Logger logger = LoggerFactory.getLogger(IncidenceHistoryController.class);

    @Autowired
    private IncidencesHistoryService incidencesHistoryService;
    @Operation(
            summary = "Retrieve all incidences history",
            description = "Get all incidences saved like history on data base.",
            tags = { "history"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Incidences.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    @GetMapping("/history")
    public ResponseEntity<List<IncidencesHistory>> getAllHistory(){
        List<IncidencesHistory> historyList = incidencesHistoryService.findAll();
        logger.info("/history getAll() ");
        return ResponseEntity.ok(historyList);
    }

    @Operation(
            summary = "Save a incidence solution to history",
            description = "Save a incidence solution to history",
            tags = { "history"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Incidences.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    @PostMapping("/history")
    public ResponseEntity<IncidencesHistory> saveToHistory(@RequestBody IncidencesHistory incidencesHistory){
        logger.info("/history saveToHistory())");
        IncidencesHistory incidenceToSave = incidencesHistoryService.saveOne(incidencesHistory);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(incidenceToSave);
    }
}

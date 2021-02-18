package com.b3_batch.controller;

import com.b3_batch.constants.AppConstants;
import com.b3_batch.domain.entity.Stock;
import com.b3_batch.dto.StockDTO;
import com.b3_batch.service.StockService;
import com.b3_batch.util.BuilderLink;
import com.b3_batch.util.HeaderUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = AppConstants.PATH + "/api/stock")
@CrossOrigin(origins = "*")
@Tag(name = "StockRestController", description = "Endpoint for stocks management")
public class StockRestController {
	
	private StockService stockService;
	private ModelMapper modelMapper;
	public BuilderLink builderLink;

	@Autowired
	public StockRestController(StockService stockService, BuilderLink builderLink) {
		super();
		this.stockService = stockService;
		this.modelMapper = new ModelMapper();
		this.builderLink = builderLink;
	}

    @Operation(description = "Creates a new stock",
            summary = "Creates a new stock. Some fields are mandatory, see the api documentation for more information!",
            responses = {@ApiResponse(content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StockDTO.class))})})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Failure")})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StockDTO> create(@RequestBody StockDTO dto) throws Exception {
        return new ResponseEntity<>(this.stockService.create(dto), HttpStatus.CREATED);
    }



    @Operation(description = "Update a stock",
            summary = "Update a stock. See the api documentation for more information!",
            responses = {@ApiResponse(content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StockDTO.class))})})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Failure")})
    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StockDTO> update(@Validated @RequestBody StockDTO dto, @PathVariable Long id) throws Exception {
    	return new ResponseEntity<>(this.stockService.update(dto, id), HttpStatus.OK);
    }

    @Operation(description = "Gets all stocks",
            summary = "Obtains stock",
            responses = {@ApiResponse(content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StockDTO.class))})})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Failure")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BuilderLink> list(Pageable pageable) throws IOException{
    	Page<Stock> page = this.stockService.list(pageable);

    	List<StockDTO> dtos = page.stream()
	    	.map(t -> modelMapper.map(t, StockDTO.class))
	    	.collect(Collectors.toList());

    	ResponseEntity<List<StockDTO>> response = new ResponseEntity<>(dtos, HeaderUtil.createPaginationHeader(page), HttpStatus.OK);
		return this.builderLink.byFilterLink(this.getClass(), "users", pageable, response.getBody(), response.getHeaders(), dtos);
    }

    @Operation(description = "Gets stock",
            summary = "Gets the stock by acronym",
            responses = {@ApiResponse(content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StockDTO.class))})})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Failure")})
    @GetMapping(value = "/by_acronym/{acronym}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StockDTO> findByAcronym(@PathVariable String acronym) throws Exception{
    	StockDTO stockDTO = this.stockService.findByAcronym(acronym);

        if (Objects.nonNull(stockDTO)) {
            return new ResponseEntity<>(stockDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(description = "Gets stock",
            summary = "Gets the stock by id",
            responses = {@ApiResponse(content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StockDTO.class))})})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Failure")})
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StockDTO> findById(@PathVariable Long id) throws Exception{
        StockDTO stockDTO = this.stockService.findById(id);

        if (Objects.nonNull(stockDTO)) {
            return new ResponseEntity<>(stockDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(description = "Delete a stock",
            summary = "Delete a stock, with past id to segment of url!",
            responses = {@ApiResponse(content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StockDTO.class))})})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Failure")})
    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws Exception {
        this.stockService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

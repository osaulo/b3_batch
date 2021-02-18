package com.b3_batch.service;

import com.b3_batch.domain.entity.Stock;
import com.b3_batch.dto.StockDTO;
import com.b3_batch.repository.StockRepository;
import com.b3_batch.validation.StockValidation;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@NoArgsConstructor
public class StockService {
	
	private StockRepository stockRepository;
	private ModelMapper modelMapper;
	private StockValidation stockValidation;

	@Autowired
	public StockService(StockRepository stockRepository,
						StockValidation stockValidation) {
		super();
		this.stockRepository = stockRepository;
		this.stockValidation = stockValidation;
		this.modelMapper = new ModelMapper();
	}
	
	@Transactional
	public StockDTO create(StockDTO stockDTO) throws Exception {
		this.stockValidation.validateUniqueAcronym(stockDTO.getAcronym());
		Stock stock = modelMapper.map(stockDTO, Stock.class);
		stock.setId(null);
		this.setAuditingEntity(stock);

		Stock save = stockRepository.save(stock);
		
		return modelMapper.map(save, StockDTO.class);
	}
	
	@Transactional
	public StockDTO update(StockDTO stockDTO, Long id_stock) throws Exception {
		Stock validateExist = stockValidation.validateExist(id_stock);
		if (!validateExist.getAcronym().equalsIgnoreCase(stockDTO.getAcronym())) {
			this.stockValidation.validateUniqueAcronym(stockDTO.getAcronym());
		}

		Stock stock = modelMapper.map(stockDTO, Stock.class);
		this.setAuditingEntity(stock);
		
		stock.setId(id_stock);
		Stock save = stockRepository.save(stock);
		
		return modelMapper.map(save, StockDTO.class);
	}

	@Transactional
	public void delete(Long id_stock) throws Exception {
		this.stockValidation.validateExist(id_stock);
		stockRepository.deleteById(id_stock);
	}

	public Page<Stock> list(Pageable pageable) throws IOException {
		return stockRepository.findAll(pageable);
	}

	public StockDTO findById(Long id_stock) throws Exception {
		Stock stock = this.stockValidation.validateExist(id_stock);

		StockDTO stockDTO = modelMapper.map(stock, StockDTO.class);
		return stockDTO;
	}

	public StockDTO findByAcronym(String acronym) throws Exception {
		Stock stock = this.stockValidation.validateExist(acronym);

		StockDTO stockDTO = modelMapper.map(stock, StockDTO.class);
		return stockDTO;
	}

	private void setAuditingEntity(Stock stock) {
		stock.setCreatedBy(stock.getAcronym());
	}
}

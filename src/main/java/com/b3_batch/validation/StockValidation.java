package com.b3_batch.validation;

import com.b3_batch.domain.entity.Stock;
import com.b3_batch.repository.StockRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@Service
@NoArgsConstructor
public class StockValidation {
	
	private StockRepository stockRepository;
	
	@Autowired
	public StockValidation(StockRepository stockRepository) {
		super();
		this.stockRepository = stockRepository;
	}

	public Stock validateExist(Long id_stock) throws Exception {

		Optional<Stock> optional = stockRepository.findById(id_stock);

		if (!optional.isPresent()) {
			throw new Exception("Stock not exist!");
		}
		
		return optional.get();
	}

	public Stock validateExist(String acronym) throws Exception {

		Optional<Stock> optional = stockRepository.findByAcronym(acronym);

		if (!optional.isPresent()) {
			throw new Exception("Stock not exist!");
		}

		return optional.get();
	}

	public void validateUniqueAcronym(String acronym) throws Exception {
		Optional<Stock> optional = stockRepository.findByAcronym(acronym);

		if (optional.isPresent()) {
			throw new ConstraintViolationException("Acronym already exists", null);
		}
	}
}

package com.project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.exceptions.UnsupportedMathOperationException;
import com.project.math.SimpleMath;
import com.project.utils.NumberConvert;

@RestController
public class MathController {
	
	SimpleMath sm = new SimpleMath();
	
	
	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo)
	throws Exception{
		if(!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return sm.sum(NumberConvert.converToDouble(numberOne),NumberConvert.converToDouble(numberTwo));	
	}

	
	@GetMapping("/subtraction/{numberOne}/{numberTwo}")
	public Double subtraction(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo)
	throws Exception{
		if(!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return sm.subtraction(NumberConvert.converToDouble(numberOne),NumberConvert.converToDouble(numberTwo));	
	}
	
	@GetMapping("/multiplication/{numberOne}/{numberTwo}")
	public Double multiplication(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo)
	throws Exception{
		if(!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return sm.multiplication(NumberConvert.converToDouble(numberOne),NumberConvert.converToDouble(numberTwo));	
	}
	
	@GetMapping("/division/{numberOne}/{numberTwo}")
	public Double division(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo)
	throws Exception{
		if(!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return sm.division(NumberConvert.converToDouble(numberOne),NumberConvert.converToDouble(numberTwo));	
	}
	
	@GetMapping("/mean/{numberOne}/{numberTwo}")
	public Double mean(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo)
	throws Exception{
		if(!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return sm.mean(NumberConvert.converToDouble(numberOne),NumberConvert.converToDouble(numberTwo));	
	}
	
	@GetMapping("/squareRoot/{numberOne}")
	public Double squareRoot(
			@PathVariable(value = "numberOne") String number)
	throws Exception{
		if(!NumberConvert.isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return Math.sqrt(NumberConvert.converToDouble(number));
	}
	

}

package th.ac.ku.bankaccount.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.ac.ku.bankaccount.data.BankAccountRepository;
import th.ac.ku.bankaccount.model.BankAccount;

@RestController
@RequestMapping("/api/bankaccount")
public class BankAccountRestController {

	private BankAccountRepository repository;

	public BankAccountRestController(BankAccountRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public List<BankAccount> getAll() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public BankAccount getOne(@PathVariable int id) {
		return repository.findById(id).get();
	}

	@PostMapping
	public BankAccount create(@RequestBody BankAccount bankAccount) {
		BankAccount record = repository.save(bankAccount);
		return record;
	}

	@PutMapping("/{id}")
	public BankAccount update(@PathVariable int id, @RequestBody BankAccount bankAccount) {
		BankAccount record = repository.findById(id).get();
		record.setBalance(bankAccount.getBalance());
		repository.save(record);
		return record;
	}

	@GetMapping("/customer/{customerId}")
	public List<BankAccount> getAllCustomerId(@PathVariable int customerId) {
		return repository.findByCustomerId(customerId);
	}

	@DeleteMapping("/{id}")
	public BankAccount delete(@PathVariable int id) {
		BankAccount record = repository.findById(id).get();
		repository.deleteById(id);
		return record;
	}

}

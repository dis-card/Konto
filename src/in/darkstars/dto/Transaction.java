package in.darkstars.dto;

import java.io.Serializable;

public class Transaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2042819256361522260L;
	private String customerId;
	private String accountType;
	private String transactionType;
	private Double amount;
	
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}

package bank.business.domain;

import java.util.ArrayList;
import java.util.List;

import bank.business.domain.Transaction.Status;

/**
 * @author Ingrid Nunes
 * 
 */
public class Branch extends OperationLocation {

	private List<Transfer> pendingTransfers;
	private List<CurrentAccount> accounts;
	private String name;

	public Branch(long number) {
		super(number);
		this.pendingTransfers = new ArrayList<>();
		this.accounts = new ArrayList<>();
	}

	public Branch(long number, String name) {
		this(number);
		this.name = name;
	}

	public void addAccount(CurrentAccount currentAccount) {
		accounts.add(currentAccount);
	}

	/**
	 * @return the accounts
	 */
	public List<CurrentAccount> getAccounts() {
		return accounts;
	}
	
	public List<Transfer> getPendingTransfers(){
		return pendingTransfers;
	}
	
	public void addPendingTransfer(Transfer transfer) {
		assert transfer.getStatus() == Status.PENDING;
		pendingTransfers.add(transfer);
	}
	
	public void approvePendingTransfer(Transfer transfer){
		
	}
	
	public void cancelPendingTransfer(Transfer transfer){
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + " (" + getNumber() + ")";
	}

}

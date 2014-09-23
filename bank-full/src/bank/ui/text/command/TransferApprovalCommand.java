package bank.ui.text.command;

import java.util.List;

import bank.business.AccountOperationService;
import bank.business.BusinessException;
import bank.business.domain.ATM;
import bank.business.domain.CurrentAccountId;
import bank.business.domain.Transaction.Status;
import bank.business.domain.Transfer;
import bank.ui.text.BankTextInterface;
import bank.ui.text.UIUtils;

public class TransferApprovalCommand extends Command {

	private final AccountOperationService accountOperationService;

	private final String YES_CODE = "S";

	private final String NO_CODE = "N";

	public TransferApprovalCommand(BankTextInterface bankInterface,
			AccountOperationService accountOperationService) {
		super(bankInterface);
		this.accountOperationService = accountOperationService;
	}

	@Override
	public void execute() throws Exception {

		List<Transfer> pendingTransfers = accountOperationService
				.getPendingTransfers();
		checkPendingTransfersEmpty(pendingTransfers);
		showPendingTransfers(pendingTransfers);
		Transfer selectedTransfer = readTransfer(pendingTransfers);
		showDetailedTransfer(selectedTransfer);
		approvalSelection(selectedTransfer);
	}

	private void showPendingTransfers(List<Transfer> pendingTransfers) {

		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append(getTextManager().getText("pending.transfers")).append("\t\t");
		sb.append(getTextManager().getText("date")).append("\t\t\t");
		sb.append(getTextManager().getText("status")).append("\t\t");
		sb.append(getTextManager().getText("location")).append("\t\t");
		sb.append(getTextManager().getText("operation.type")).append("\t\n");
		sb.append("---------------------------------------------------------------------------------------------------------------\n");
		for (Transfer transfer : pendingTransfers) {
			sb.append(pendingTransfers.indexOf(transfer) + "\t\t\t\t");
			sb.append(UIUtils.INSTANCE.formatDateTime(transfer.getDate()))
					.append("\t");
			if (transfer.getStatus() == Status.PENDING)
				sb.append(getTextManager().getText("status.pending"));

			sb.append("\t");
			sb.append(transfer.getLocation()).append("\t");
			if (transfer.getLocation() instanceof ATM)
				sb.append("\t");
			sb.append(
					getTextManager().getText(
							"operation." + transfer.getClass().getSimpleName()))
					.append("\t\t\n");
		}
		System.out.println(sb);

	}

	private Transfer readTransfer(List<Transfer> pendingTransfers) {
			Transfer selectedTransfer = null;
			if(pendingTransfers != null) {
				Integer option = UIUtils.INSTANCE.readInteger("message.choose.transfer", 0, pendingTransfers.size()-1);
				selectedTransfer = pendingTransfers.get(option);
			}
			return selectedTransfer;
	}

	private void showDetailedTransfer(Transfer selectedTransfer) {

		StringBuffer sb = new StringBuffer();
		if (selectedTransfer != null) {
			sb.append("\n" + getTextManager().getText("source.account"))
					.append("\t");
			sb.append(getTextManager().getText("destination.account")).append(
					"\t");
			sb.append(getTextManager().getText("amount")).append("\n");
			sb.append("----------------------------------------\n");
			CurrentAccountId srcId = selectedTransfer.getAccount().getId();
			sb.append("AG ").append(srcId.getBranch().getNumber())
					.append(" C/C ").append(srcId.getNumber()).append("\t");
			CurrentAccountId dstId = selectedTransfer.getDestinationAccount()
					.getId();
			sb.append("AG ").append(dstId.getBranch().getNumber())
					.append(" C/C ").append(dstId.getNumber()).append("\t");
			sb.append("+ ");
			sb.append(selectedTransfer.getAmount());
			sb.append("\n");
			System.out.println(sb);
		}
	}

	private void approvalSelection(Transfer selectedTransfer)
			throws BusinessException {
		String approval = null;
		if (selectedTransfer != null) {
			do {
				approval = UIUtils.INSTANCE
						.readString("message.transfer.approval.options");
				switch (approval) {
				case YES_CODE:
					accountOperationService
							.approvePendingTransfer(selectedTransfer);
					System.out.println(getTextManager().getText(
							"message.authorized.transfer"));
					return;
				case NO_CODE:
					accountOperationService
							.cancelPendingTransfer(selectedTransfer);
					System.out.println(getTextManager().getText(
							"message.canceled.transfer"));
					System.out.println(approval);
					return;
				}
				approval = UIUtils.INSTANCE
						.readString("message.transfer.approval.options");
			} while (!approval.equals(YES_CODE) && !approval.equals(NO_CODE));
		}
	}

	private void checkPendingTransfersEmpty(List<Transfer> pendingTransfers)
			throws BusinessException {
		if (pendingTransfers.isEmpty())
			throw new BusinessException("exception.empty.pending.transfers");
	}
}

package bank.ui.graphic.action;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import bank.business.AccountOperationService;
import bank.business.BusinessException;
import bank.ui.TextManager;
import bank.ui.graphic.BankGraphicInterface;
import bank.ui.graphic.GUIUtils;

public class ApproveTransferAction extends AccountAbstractAction {

	private static final long serialVersionUID = 5090183202921964451L;
	private JFormattedTextField approval;
	private JDialog dialog;
			

	public ApproveTransferAction(BankGraphicInterface bankInterface,
			TextManager textManager,
			AccountOperationService accountOperationService) {
		super(bankInterface, textManager, accountOperationService);

		super.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		super.putValue(Action.NAME, textManager.getText("action.approve"));
	}
	public void execute() throws Exception {}
/*
	@Override
	public void execute() throws Exception {

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel subpanel = new JPanel(new GridLayout(3, 2, 5, 5));

		initAndAddAccountFields(subpanel);

		this.approval = new JFormattedTextField(
				NumberFormat.getCurrencyInstance());
		approval.setColumns(10);
		approval.setEditable(false);
		subpanel.add(new JLabel(textManager.getText("approve") + ":"));
		subpanel.add(approval);

		panel.add(subpanel, BorderLayout.CENTER);

		Long branchId = bankInterface.readBranchId();
		Long currentAccountNumber = bankInterface.readCurrentAccountNumber();

		if (branchId == null) {
			assert currentAccountNumber == null;
			subpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JButton ok = new JButton(textManager.getText("button.ok"));
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setApprovalTab();
				}
			});
			subpanel.add(ok);
			panel.add(subpanel, BorderLayout.SOUTH);
		} else {
			setApprovalTab();
		}

		this.dialog = GUIUtils.INSTANCE.createDialog(bankInterface.getFrame(),
				"action.balance", panel);
		this.dialog.setVisible(true);
	
		
	}
	
	private void setApprovalTab() {
		try {
			if (!checkAccountFields())
				return;
			double balance = accountOperationService.getBalance(
					((Number) branch.getValue()).longValue(),
					((Number) accountNumber.getValue()).longValue());
			this.approval.setValue(balance);
		} catch (BusinessException be) {
			GUIUtils.INSTANCE.showMessage(bankInterface.getFrame(), be.getMessage(), be.getArgs(),
					JOptionPane.WARNING_MESSAGE);
			log.warn(be);
		} catch (Exception exc) {
			GUIUtils.INSTANCE.handleUnexceptedError(bankInterface.getFrame(), exc);
		}
	}
	
*/	
}

package listener;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class JTADocumentListener implements DocumentListener {

	private final JTextArea area;
	private int hSize = 1;
	private int vSize = 1;

	public JTADocumentListener(JTextArea area) {
		this.area = area;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		adjust();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		adjust();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		adjust();
	}

	private void adjust() {
		int maxColumns = getMaxColumns();
		if ((area.getLineCount() != vSize) || (maxColumns != hSize)) {
			hSize = maxColumns;
			vSize = area.getLineCount();
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					area.setColumns(hSize);
					area.setRows(vSize);
				}
			});
		}

	}

	private int getMaxColumns() {
		int startOffset = 0;
		int maxColumns = 0;
		for (int i = 0; i < area.getLineCount(); i++) {
			try {
				int endOffset = area.getLineEndOffset(i);
				int lineSize = endOffset - startOffset;
				if (lineSize > maxColumns) {
					maxColumns = lineSize;
				}
				startOffset = endOffset;
			} catch (BadLocationException ble) {
			}
		}

		return maxColumns;
	}
}

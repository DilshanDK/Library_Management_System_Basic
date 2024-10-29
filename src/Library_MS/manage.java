package Library_MS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public final class manage extends javax.swing.JFrame {

DBconnect DBconnect = new DBconnect();
Connection conn;
PreparedStatement pst;
ResultSet rs;

public manage() {
    initComponents();
    conn = (Connection) Library_MS.DBconnect.connection();
    books();
    bookTableData();
}


//Three Panels Loading methods
public void books() {
    lblBooks.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 22));
    lblReaders.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 14));
    lblAnalysis.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 14));
    lblStatus.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 14));

    panWinBooks.setVisible(true);
    panWinReaders.setVisible(false);
    panWinAnalysis.setVisible(false);
    panWinStatus.setVisible(false);

}
public void readers() {
    lblBooks.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 14));
    lblReaders.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 22));
    lblAnalysis.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 14));
    lblStatus.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 14));

    panWinBooks.setVisible(false);
    panWinReaders.setVisible(true);
    panWinAnalysis.setVisible(false);
    panWinStatus.setVisible(false);

}
public void analysis() {
    lblBooks.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 14));
    lblReaders.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 14));
    lblAnalysis.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 22));
    lblStatus.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 14));

    panWinBooks.setVisible(false);
    panWinReaders.setVisible(false);
    panWinAnalysis.setVisible(true);
    panWinStatus.setVisible(false);

}
public void status() {
    lblBooks.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 14));
    lblReaders.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 14));
    lblAnalysis.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 14));
    lblStatus.setFont(lblBooks.getFont().deriveFont(Font.BOLD, 22));
    panWinBooks.setVisible(false);
    panWinReaders.setVisible(false);
    panWinAnalysis.setVisible(false);
    panWinStatus.setVisible(true);
}


//Tab designs
public void tabEnterBooks() {
    panTabBooks.setBackground(Color.white);
    lblBooks.setForeground(Color.black);
}
public void tabEnterReaders() {
    panTabReaders.setBackground(Color.white);
    lblReaders.setForeground(Color.black);
}
public void tabEnterAnalysis() {
    panTabAnalysis.setBackground(Color.white);
    lblAnalysis.setForeground(Color.black);
}
public void tabEnterStatus() {
    panTabStatus.setBackground(Color.white);
    lblStatus.setForeground(Color.black);
}
public void tabExitBooks() {
    panTabBooks.setBackground(new Color(55, 170, 133));
    lblBooks.setForeground(Color.white);
}
public void tabExitReaders() {
    panTabReaders.setBackground(new Color(55, 170, 133));
    lblReaders.setForeground(Color.white);
}
public void tabExitAnalysis() {
    panTabAnalysis.setBackground(new Color(55, 170, 133));
    lblAnalysis.setForeground(Color.white);
}
public void tabExitStatus() {
    panTabStatus.setBackground(new Color(55, 170, 133));
    lblStatus.setForeground(Color.white);
}


//Data set load and other functions for Books table
public void bookTableData() {

    try {
        String sqlBook = "SELECT book_id as \"ID\", book_name as \"Name of The Book\", book_author as \"Author of The Book\", book_type as \"Type of The Book\",book_quantity as \"Quantity\" FROM `books`";
        pst = conn.prepareStatement(sqlBook);
        rs = pst.executeQuery();
        tableBooks.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
public void SearchBooks() throws SQLException {

    try {
        String searchBook = txtSearchBooks.getText();
        String sql = "SELECT book_id as \"ID\", book_name as \"Name of The Book\", book_author as \"Author of The Book\", book_type as \"Type of The Book\",book_quantity as \"Quantity\" FROM `books` WHERE book_name LIKE'%" + searchBook + "%' or book_type LIKE'%" + searchBook + "%' or book_author LIKE'%" + searchBook + "%' or book_id LIKE'%" + searchBook + "%' or book_quantity LIKE'%" + searchBook + "%'";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        tableBooks.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);

    }
}
public void backToBTxt() {
    int row = tableBooks.getSelectedRow();
    String backBName = tableBooks.getValueAt(row, 1).toString();
    String backBAuthor = tableBooks.getValueAt(row, 2).toString();
    String backBType = tableBooks.getValueAt(row, 3).toString();
    String backBQuantity = tableBooks.getValueAt(row, 4).toString();

    txtBName.setText(backBName);
    txtBAuthor.setText(backBAuthor);
    txtBType.setText(backBType);
    txtBQuantity.setText(backBQuantity);

}
public void newBookData() throws SQLException {

    String bName = txtBName.getText();
    String bAuthor = txtBAuthor.getText();
    String bType = txtBType.getText();
    String bQuantity = txtBQuantity.getText();
    try {
        String sql = "SELECT COUNT(*) FROM books WHERE book_name='" + bName + "'";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
    if (rs.next() && rs.getInt(1) > 0) {
        JOptionPane.showMessageDialog(null, "This Book is Exists!");
    } else {
        if (!bName.isEmpty() && !bAuthor.isEmpty() && !bType.isEmpty() && !bQuantity.isEmpty()) {
            try {
                String sql = "INSERT INTO `books`(`book_name`, `book_author`, `book_type`,book_quantity) VALUES ('" + bName + "','" + bAuthor + "','" + bType + "','" + bQuantity + "')";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                bookTableData();
                JOptionPane.showMessageDialog(null, "Entered Successfully!");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Invalid Entry!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Entry!");
        }
    }
}
public void removeBData() {

    int row = tableBooks.getSelectedRow();
    if (row > -1) {
        String backBName = tableBooks.getValueAt(row, 1).toString();
        String backBAuthor = tableBooks.getValueAt(row, 2).toString();
        String backBType = tableBooks.getValueAt(row, 3).toString();
        String backBQuantity = tableBooks.getValueAt(row, 4).toString();
        JOptionPane.showMessageDialog(null, "Are You Sure Remove This Data?");
        int result = JOptionPane.showConfirmDialog(null, "Confirm", "Confirm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM `books` WHERE `book_name`='" + backBName + "' AND `book_author`='" + backBAuthor + "' AND `book_type`='" + backBType + "' AND `book_quantity`='" + backBQuantity + "'";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please Select Book Row!");
    }
}
public void clearBData() {
    txtBName.setText("");
    txtBAuthor.setText("");
    txtBType.setText("");
    txtBQuantity.setText("");

}
public void updateBData() {

    int row = tableBooks.getSelectedRow();
    if (row > -1) {
        String bId = tableBooks.getValueAt(row, 0).toString();
        String bName = txtBName.getText();
        String bAuthor = txtBAuthor.getText();
        String bType = txtBType.getText();
        String bQuantity = txtBQuantity.getText();

        if (!bName.isEmpty() || !bAuthor.isEmpty() || !bType.isEmpty() || !bQuantity.isEmpty()) {
            if (!bName.isEmpty()) {
                try {
                    String sql = "UPDATE `books` SET  `book_name`='" + bName + "' WHERE book_id='" + bId + "'";
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (!bAuthor.isEmpty()) {
                try {
                    String sql = "UPDATE `books` SET  `book_author`='" + bAuthor + "' WHERE book_id='" + bId + "'";
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (!bType.isEmpty()) {
                try {
                    String sql = "UPDATE `books` SET  `book_type`='" + bType + "' WHERE book_id='" + bId + "'";
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (!bQuantity.isEmpty()) {
                try {
                    String sql = "UPDATE `books` SET  `book_quantity`='" + bQuantity + "' WHERE book_id='" + bId + "'";
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            JOptionPane.showMessageDialog(null, "Book Details Updated!");

        } else {
            JOptionPane.showMessageDialog(null, "Invalid Entry!");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Not Selected Row!");
    }
}
public void statusBookData() {

    String statusBId = txtSBId.getText();
    if (!statusBId.isEmpty()) {
        try {
            String sqlBook = "SELECT * FROM `books` where book_id='" + statusBId + "'";
            pst = conn.prepareStatement(sqlBook);
            rs = pst.executeQuery();
            while (rs.next()) {
                String sBId = rs.getString(1);
                String sBName = rs.getString(2);
                String sBAuthor = rs.getString(3);
                String sBType = rs.getString(4);
                String sBQuantity = rs.getString(5);

                lblSBId.setText("Book ID :" + " " + sBId);
                lblSBName.setText("Name :" + " " + sBName);
                lblSBAuthor.setText("Author :" + " " + sBAuthor);
                lblSBType.setText("Type :" + " " + sBType);
                lblSBQuantity.setText("Quantity :" + " " + sBQuantity);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    } else {
        lblSBId.setText("Book ID :");
        lblSBName.setText("Name :");
        lblSBAuthor.setText("Author :");
        lblSBType.setText("Type :");
        lblSBQuantity.setText("Quantity :");
    }

}


//Data set load and other functions for Reader table
public void readerTableData() {

    try {
        String sqlReader = "SELECT reader_id as \"ID of The Reader\", `reader_name` as \"Name of The Reader\", `reader_age` as \"Age of The Reader\", `reader_city` as \"City of The Reader\" FROM `readers`";
        pst = conn.prepareStatement(sqlReader);
        rs = pst.executeQuery();
        tableReaders.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
public void SearchReaders() throws SQLException {

    try {
        String searchReader = txtSearchReaders.getText();
        String sql = "SELECT reader_id as \"ID of The Reader\", reader_name as \"Name of The Reader\", reader_age as \"Age of The Reader\", reader_city as \"City of The Reader\" FROM `readers` WHERE reader_id LIKE'%" + searchReader + "%' or reader_name LIKE'%" + searchReader + "%' or reader_age LIKE'%" + searchReader + "%' or reader_city LIKE'%" + searchReader + "%'";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        tableReaders.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);

    }
}
public void backToRTxt() {
    int row = tableReaders.getSelectedRow();
    String backRName = tableReaders.getValueAt(row, 1).toString();
    String backRAge = tableReaders.getValueAt(row, 2).toString();
    String backRCity = tableReaders.getValueAt(row, 3).toString();

    txtRName.setText(backRName);
    txtRAge.setText(backRAge);
    txtRCity.setText(backRCity);
}
public void newReaderData() throws SQLException {

    String rName = txtRName.getText();
    String rAge = txtRAge.getText();
    String rCity = txtRCity.getText();
    try {
        String sql = "SELECT COUNT(*) FROM readers WHERE reader_name='" + rName + "' && reader_age='" + rAge + "' && reader_city='" + rCity + "'";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
    if (rs.next() && rs.getInt(1) > 0) {
        JOptionPane.showMessageDialog(null, "This Reader is Exists!");
    } else {
        if (!rName.isEmpty() && !rAge.isEmpty() && !rCity.isEmpty()) {
            try {
                String sql = "INSERT INTO `readers`(`reader_name`, `reader_age`, `reader_city`) VALUES ('" + rName + "','" + rAge + "','" + rCity + "')";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                readerTableData();
                JOptionPane.showMessageDialog(null, "Entered Successfully!");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Invalid Entry!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Entry!");
        }
    }
}
public void removeRData() {

    int row = tableReaders.getSelectedRow();
    if (row > -1) {
        String backRName = tableReaders.getValueAt(row, 1).toString();
        String backRAge = tableReaders.getValueAt(row, 2).toString();
        String backRCity = tableReaders.getValueAt(row, 3).toString();
        JOptionPane.showMessageDialog(null, "Are You Sure Remove This Data?");
        int result = JOptionPane.showConfirmDialog(null, "Confirm", "Confirm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM `readers` WHERE `reader_name`='" + backRName + "' AND `reader_age`='" + backRAge + "' AND `reader_city`='" + backRCity + "'";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please Select Reader Row!");
    }
}
public void clearRData() {
    txtRName.setText("");
    txtRAge.setText("");
    txtRCity.setText("");
}
public void updateRData() {

    int row = tableReaders.getSelectedRow();
    if (row > -1) {
        String rId = tableReaders.getValueAt(row, 0).toString();
        String rName = txtRName.getText();
        String rAge = txtRAge.getText();
        String rCity = txtRCity.getText();

        if (!rName.isEmpty() || !rAge.isEmpty() || !rCity.isEmpty()) {
            if (!rName.isEmpty()) {
                try {
                    String sql = "UPDATE `readers` SET  `reader_name`='" + rName + "' WHERE reader_id='" + rId + "'";
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (!rAge.isEmpty()) {
                try {
                    String sql = "UPDATE `readers` SET  `reader_age`='" + rAge + "' WHERE reader_id='" + rId + "'";
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if (!rCity.isEmpty()) {
                try {
                    String sql = "UPDATE `readers` SET  `reader_city`='" + rCity + "' WHERE reader_id='" + rId + "'";
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            JOptionPane.showMessageDialog(null, "Reader Details Updated!");

        } else {
            JOptionPane.showMessageDialog(null, "Invalid Entry!");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Not Selected Row!");
    }

}
public void statusReaderData() {
    String statusRId = txtSRId.getText();
    if (!statusRId.isEmpty()) {
        try {
            String sqlReader = "SELECT * FROM `readers` where reader_id='" + statusRId + "'";
            pst = conn.prepareStatement(sqlReader);
            rs = pst.executeQuery();
            while (rs.next()) {
                String sRId = rs.getString(1);
                String sRName = rs.getString(2);
                String sRAge = rs.getString(3);
                String sRCity = rs.getString(4);

                lblSRId.setText("Reader ID :" + " " + sRId);
                lblSRName.setText("Name :" + " " + sRName);
                lblSRAge.setText("Age :" + " " + sRAge);
                lblSRCity.setText("City :" + " " + sRCity);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    } else {

        lblSRId.setText("Reader ID :");
        lblSRName.setText("Name :");
        lblSRAge.setText("Age :");
        lblSRCity.setText("City :");

    }
}


//Book Issue functions
public void quantityIssueUpdate() {
    String bId = txtSBId.getText();
    try {
        String sqlIssueUpdate = "UPDATE `books` SET  `book_quantity`=`book_quantity`-1 WHERE book_id='" + bId + "'";
        pst = conn.prepareStatement(sqlIssueUpdate);
        pst.executeUpdate();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
public void issueBook() throws SQLException {
    String bId = txtSBId.getText();

    try {
        String sql = "SELECT SUM(`book_quantity`) FROM `books` WHERE `book_id`='" + bId + "'";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }

    if (rs.next() && rs.getInt(1) > 0) {
        String rId = txtSBId.getText();
        Date fIssueDate = selecDateIssue.getDatoFecha();
        Date fReturnDate = selecDateReturn.getDatoFecha();

        long l1 = fIssueDate.getTime();
        long l2 = fReturnDate.getTime();

        java.sql.Date issueDate = new java.sql.Date(l1);
        java.sql.Date returnDate = new java.sql.Date(l2);

        if (!rId.isEmpty() && !bId.isEmpty() && issueDate != null && returnDate != null) {
            try {
                String sql = "INSERT INTO `issue_books`(`book_id`,`reader_id`,`issue_date`,returned_date,status) VALUES ('" + bId + "','" + rId + "','" + issueDate + "','" + returnDate + "','Pennding')";
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                quantityIssueUpdate();
                JOptionPane.showMessageDialog(null, "Book Issued");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "invald");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Entry!");
        }

    } else {
        JOptionPane.showMessageDialog(null, "The Book is Not Avaialbe");

    }
}
public void details() {

    try {
        String sqlTotBooks = "SELECT COUNT(book_id) FROM books";
        pst = conn.prepareStatement(sqlTotBooks);
        rs = pst.executeQuery();
        if (rs.next()) {
            int id = rs.getInt(1);
            lblTotBooks.setText("Total Books: " + id);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Invalid Entry");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
    try {
        String sqlTotReaders = "SELECT COUNT(reader_id) FROM readers";
        pst = conn.prepareStatement(sqlTotReaders);
        rs = pst.executeQuery();
        if (rs.next()) {
            int id = rs.getInt(1);
            lblTotReaders.setText("Total Readers: " + id);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Invalid Entry");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
    try {
        String sqlTotIssue = "SELECT COUNT(issue_id) FROM issue_books";
        pst = conn.prepareStatement(sqlTotIssue);
        rs = pst.executeQuery();
        if (rs.next()) {
            int id = rs.getInt(1);
            lblTotIssued.setText("Total Issued: " + id);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Invalid Entry");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
}

//Book Return functions
public void returnBook() {
    int row = tableIssue.getSelectedRow();
    if (row > -1) {
        try {
            String iId = tableIssue.getValueAt(row, 0).toString();
            String bs = tableIssue.getValueAt(row, 5).toString();
            String bId = tableIssue.getValueAt(row, 2).toString();
            String msg = "Returned";
            if ("Pennding".equals(bs)) {
                String sqlReturnBStatus = "UPDATE `issue_books` SET `status`='" + msg + "' WHERE `issue_id`='" + iId + "'";
                pst = conn.prepareStatement(sqlReturnBStatus);
                pst.executeUpdate();
                String sqlReturnBQuntity = "UPDATE `books` SET  `book_quantity`=`book_quantity`+1 WHERE book_id='" + bId + "'";
                pst = conn.prepareStatement(sqlReturnBQuntity);
                pst.executeUpdate();
                issueTableData();
                JOptionPane.showMessageDialog(null, "Book Returned!");

            } else {
                JOptionPane.showMessageDialog(null, "Its Already Returned!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please Seclect Row!");
    }

}
public void issueTableData() {

    try {
        String sqlIssue = "SELECT `issue_id` as \"Issue ID\", `book_id` as \"Book ID\", `reader_id` as \"Reader ID\", `issue_date` as \"Issued Date\", `returned_date` as \"Return Date\", `status` as \"Status\" FROM `issue_books`";
        pst = conn.prepareStatement(sqlIssue);
        rs = pst.executeQuery();
        tableIssue.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
public void SearchIssued() {

    try {
        String searchIssue = txtIssue.getText();
        String sql = "SELECT `issue_id` as \"Issue ID\", `book_id` as \"Book ID\", `reader_id` as \"Reader ID\", `issue_date` as \"Issued Date\", `returned_date` as \"Return Date\", `status` as \"Status\" FROM `issue_books` WHERE issue_id LIKE'%" + searchIssue + "%' or book_id LIKE'%" + searchIssue + "%' or reader_id LIKE'%" + searchIssue + "%' or issue_date LIKE'%" + searchIssue + "%' or returned_date LIKE'%" + searchIssue + "%' or status LIKE'%" + searchIssue + "%'";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        tableIssue.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);

    }
}



// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
private void initComponents() {

    panMenu = new javax.swing.JPanel();
    panTabBooks = new javax.swing.JPanel();
    lblBooks = new javax.swing.JLabel();
    panTabReaders = new javax.swing.JPanel();
    lblReaders = new javax.swing.JLabel();
    panTabAnalysis = new javax.swing.JPanel();
    lblAnalysis = new javax.swing.JLabel();
    panTabStatus = new javax.swing.JPanel();
    lblStatus = new javax.swing.JLabel();
    panWinBooks = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();
    jLabel9 = new javax.swing.JLabel();
    jPanel7 = new javax.swing.JPanel();
    txtSearchBooks = new javax.swing.JTextField();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableBooks = new javax.swing.JTable();
    btnBNew = new javax.swing.JButton();
    btnBUpdate = new javax.swing.JButton();
    btnBClear = new javax.swing.JButton();
    btnBCancal = new javax.swing.JButton();
    btnBRemove = new javax.swing.JButton();
    txtBName = new javax.swing.JTextField();
    txtBAuthor = new javax.swing.JTextField();
    txtBType = new javax.swing.JTextField();
    lblBType = new javax.swing.JLabel();
    lblBAuthro = new javax.swing.JLabel();
    lblBName = new javax.swing.JLabel();
    txtBQuantity = new javax.swing.JTextField();
    lblBType1 = new javax.swing.JLabel();
    panWinReaders = new javax.swing.JPanel();
    jPanel1 = new javax.swing.JPanel();
    jLabel6 = new javax.swing.JLabel();
    jPanel8 = new javax.swing.JPanel();
    txtSearchReaders = new javax.swing.JTextField();
    btnRCancal = new javax.swing.JButton();
    btnRNew = new javax.swing.JButton();
    btnRRemove = new javax.swing.JButton();
    btnRUpdate = new javax.swing.JButton();
    btnRClear = new javax.swing.JButton();
    txtRAge = new javax.swing.JTextField();
    txtRName = new javax.swing.JTextField();
    txtRCity = new javax.swing.JTextField();
    lblRName = new javax.swing.JLabel();
    lblRAge = new javax.swing.JLabel();
    lblRCity = new javax.swing.JLabel();
    jScrollPane2 = new javax.swing.JScrollPane();
    tableReaders = new javax.swing.JTable();
    panWinStatus = new javax.swing.JPanel();
    jPanel4 = new javax.swing.JPanel();
    jLabel8 = new javax.swing.JLabel();
    jPanel5 = new javax.swing.JPanel();
    jPanel6 = new javax.swing.JPanel();
    lblSBName = new javax.swing.JLabel();
    lblSBAuthor = new javax.swing.JLabel();
    lblSBType = new javax.swing.JLabel();
    lblSBQuantity = new javax.swing.JLabel();
    lblSBId = new javax.swing.JLabel();
    jPanel9 = new javax.swing.JPanel();
    lblSRId = new javax.swing.JLabel();
    lblSRAge = new javax.swing.JLabel();
    lblSRCity = new javax.swing.JLabel();
    lblSRName = new javax.swing.JLabel();
    selecDateIssue = new rojeru_san.componentes.RSDateChooser();
    selecDateReturn = new rojeru_san.componentes.RSDateChooser();
    btnIBook = new rojerusan.RSMaterialButtonRectangle();
    lblSBQuantity1 = new javax.swing.JLabel();
    lblSBQuantity2 = new javax.swing.JLabel();
    txtSBId = new rojerusan.RSMetroTextPlaceHolder();
    txtSRId = new rojerusan.RSMetroTextPlaceHolder();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    panWinAnalysis = new javax.swing.JPanel();
    jPanel3 = new javax.swing.JPanel();
    jLabel7 = new javax.swing.JLabel();
    jPanel10 = new javax.swing.JPanel();
    jScrollPane3 = new javax.swing.JScrollPane();
    tableIssue = new javax.swing.JTable();
    lblIssued = new javax.swing.JPanel();
    lblTotIssued = new javax.swing.JLabel();
    jPanel12 = new javax.swing.JPanel();
    lblTotReaders = new javax.swing.JLabel();
    jPanel13 = new javax.swing.JPanel();
    lblTotBooks = new javax.swing.JLabel();
    btnRBook = new rojerusan.RSMaterialButtonRectangle();
    txtIssue = new javax.swing.JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Library Management System");
    setBackground(new java.awt.Color(255, 255, 255));

    panMenu.setBackground(new java.awt.Color(20, 77, 122));

    panTabBooks.setBackground(new java.awt.Color(55, 170, 133));
    panTabBooks.setForeground(new java.awt.Color(255, 255, 255));
    panTabBooks.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            panTabBooksMouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            panTabBooksMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            panTabBooksMouseExited(evt);
        }
    });

    lblBooks.setFont(new java.awt.Font("Poppins Light", 1, 20)); // NOI18N
    lblBooks.setForeground(new java.awt.Color(255, 255, 255));
    lblBooks.setText("Books");

    javax.swing.GroupLayout panTabBooksLayout = new javax.swing.GroupLayout(panTabBooks);
    panTabBooks.setLayout(panTabBooksLayout);
    panTabBooksLayout.setHorizontalGroup(
        panTabBooksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panTabBooksLayout.createSequentialGroup()
            .addGap(64, 64, 64)
            .addComponent(lblBooks)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    panTabBooksLayout.setVerticalGroup(
        panTabBooksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panTabBooksLayout.createSequentialGroup()
            .addContainerGap(19, Short.MAX_VALUE)
            .addComponent(lblBooks)
            .addGap(15, 15, 15))
    );

    panTabReaders.setBackground(new java.awt.Color(55, 170, 133));
    panTabReaders.setForeground(new java.awt.Color(255, 255, 255));
    panTabReaders.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            panTabReadersMouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            panTabReadersMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            panTabReadersMouseExited(evt);
        }
    });

    lblReaders.setFont(new java.awt.Font("Poppins Light", 1, 12)); // NOI18N
    lblReaders.setForeground(new java.awt.Color(255, 255, 255));
    lblReaders.setText("Readers");

    javax.swing.GroupLayout panTabReadersLayout = new javax.swing.GroupLayout(panTabReaders);
    panTabReaders.setLayout(panTabReadersLayout);
    panTabReadersLayout.setHorizontalGroup(
        panTabReadersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panTabReadersLayout.createSequentialGroup()
            .addGap(64, 64, 64)
            .addComponent(lblReaders, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    panTabReadersLayout.setVerticalGroup(
        panTabReadersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panTabReadersLayout.createSequentialGroup()
            .addContainerGap(18, Short.MAX_VALUE)
            .addComponent(lblReaders)
            .addGap(16, 16, 16))
    );

    panTabAnalysis.setBackground(new java.awt.Color(55, 170, 133));
    panTabAnalysis.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            panTabAnalysisMouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            panTabAnalysisMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            panTabAnalysisMouseExited(evt);
        }
    });

    lblAnalysis.setBackground(new java.awt.Color(255, 255, 255));
    lblAnalysis.setFont(new java.awt.Font("Poppins Light", 1, 12)); // NOI18N
    lblAnalysis.setForeground(new java.awt.Color(255, 255, 255));
    lblAnalysis.setText("Analysis");

    javax.swing.GroupLayout panTabAnalysisLayout = new javax.swing.GroupLayout(panTabAnalysis);
    panTabAnalysis.setLayout(panTabAnalysisLayout);
    panTabAnalysisLayout.setHorizontalGroup(
        panTabAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panTabAnalysisLayout.createSequentialGroup()
            .addGap(64, 64, 64)
            .addComponent(lblAnalysis, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    panTabAnalysisLayout.setVerticalGroup(
        panTabAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panTabAnalysisLayout.createSequentialGroup()
            .addContainerGap(18, Short.MAX_VALUE)
            .addComponent(lblAnalysis)
            .addGap(16, 16, 16))
    );

    panTabStatus.setBackground(new java.awt.Color(55, 170, 133));
    panTabStatus.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            panTabStatusMouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            panTabStatusMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            panTabStatusMouseExited(evt);
        }
    });

    lblStatus.setBackground(new java.awt.Color(255, 255, 255));
    lblStatus.setFont(new java.awt.Font("Poppins Light", 1, 12)); // NOI18N
    lblStatus.setForeground(new java.awt.Color(255, 255, 255));
    lblStatus.setText("Status");

    javax.swing.GroupLayout panTabStatusLayout = new javax.swing.GroupLayout(panTabStatus);
    panTabStatus.setLayout(panTabStatusLayout);
    panTabStatusLayout.setHorizontalGroup(
        panTabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panTabStatusLayout.createSequentialGroup()
            .addGap(65, 65, 65)
            .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(50, Short.MAX_VALUE))
    );
    panTabStatusLayout.setVerticalGroup(
        panTabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panTabStatusLayout.createSequentialGroup()
            .addGap(17, 17, 17)
            .addComponent(lblStatus)
            .addContainerGap(17, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout panMenuLayout = new javax.swing.GroupLayout(panMenu);
    panMenu.setLayout(panMenuLayout);
    panMenuLayout.setHorizontalGroup(
        panMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(panTabStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(panTabAnalysis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(panTabReaders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(panTabBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    panMenuLayout.setVerticalGroup(
        panMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMenuLayout.createSequentialGroup()
            .addContainerGap(185, Short.MAX_VALUE)
            .addComponent(panTabBooks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(12, 12, 12)
            .addComponent(panTabReaders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(panTabAnalysis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(panTabStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(133, 133, 133))
    );

    panWinBooks.setBackground(new java.awt.Color(20, 77, 122));
    panWinBooks.setPreferredSize(new java.awt.Dimension(832, 537));

    jPanel2.setBackground(new java.awt.Color(0, 153, 204));

    jLabel9.setBackground(new java.awt.Color(0, 153, 255));
    jLabel9.setFont(new java.awt.Font("Poppins SemiBold", 1, 36)); // NOI18N
    jLabel9.setForeground(new java.awt.Color(255, 255, 255));
    jLabel9.setText("Books");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(272, 272, 272))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap(9, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    jPanel7.setBackground(new java.awt.Color(20, 77, 122));

    txtSearchBooks.setBackground(new java.awt.Color(20, 77, 122));
    txtSearchBooks.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
    txtSearchBooks.setForeground(new java.awt.Color(255, 255, 255));
    txtSearchBooks.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Books", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Poppins Light", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
    txtSearchBooks.setName("txtSearchBooks"); // NOI18N
    txtSearchBooks.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtSearchBooksKeyReleased(evt);
        }
    });

    tableBooks.setAutoCreateRowSorter(true);
    tableBooks.setBackground(new java.awt.Color(221, 221, 221));
    tableBooks.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
    tableBooks.setForeground(new java.awt.Color(0, 0, 0));
    tableBooks.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, true, true, true
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    tableBooks.setMaximumSize(new java.awt.Dimension(0, 0));
    tableBooks.setMinimumSize(new java.awt.Dimension(0, 0));
    tableBooks.setSelectionBackground(new java.awt.Color(0, 255, 204));
    tableBooks.setShowGrid(true);
    tableBooks.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tableBooksMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tableBooks);
    if (tableBooks.getColumnModel().getColumnCount() > 0) {
        tableBooks.getColumnModel().getColumn(1).setResizable(false);
    }

    btnBNew.setBackground(new java.awt.Color(26, 188, 156));
    btnBNew.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    btnBNew.setForeground(new java.awt.Color(0, 0, 0));
    btnBNew.setText("New");
    btnBNew.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnBNewActionPerformed(evt);
        }
    });

    btnBUpdate.setBackground(new java.awt.Color(26, 188, 156));
    btnBUpdate.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    btnBUpdate.setForeground(new java.awt.Color(0, 0, 0));
    btnBUpdate.setText("Update");
    btnBUpdate.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnBUpdateActionPerformed(evt);
        }
    });

    btnBClear.setBackground(new java.awt.Color(26, 188, 156));
    btnBClear.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    btnBClear.setForeground(new java.awt.Color(0, 0, 0));
    btnBClear.setText("Clear");
    btnBClear.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnBClearActionPerformed(evt);
        }
    });

    btnBCancal.setBackground(new java.awt.Color(26, 188, 156));
    btnBCancal.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    btnBCancal.setForeground(new java.awt.Color(0, 0, 0));
    btnBCancal.setText("Cancal");
    btnBCancal.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnBCancalActionPerformed(evt);
        }
    });

    btnBRemove.setBackground(new java.awt.Color(26, 188, 156));
    btnBRemove.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    btnBRemove.setForeground(new java.awt.Color(0, 0, 0));
    btnBRemove.setText("Remove");
    btnBRemove.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnBRemoveActionPerformed(evt);
        }
    });

    txtBName.setBackground(new java.awt.Color(44, 62, 80));
    txtBName.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
    txtBName.setForeground(new java.awt.Color(255, 255, 255));
    txtBName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

    txtBAuthor.setBackground(new java.awt.Color(44, 62, 80));
    txtBAuthor.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
    txtBAuthor.setForeground(new java.awt.Color(255, 255, 255));
    txtBAuthor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

    txtBType.setBackground(new java.awt.Color(44, 62, 80));
    txtBType.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
    txtBType.setForeground(new java.awt.Color(255, 255, 255));
    txtBType.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

    lblBType.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
    lblBType.setForeground(new java.awt.Color(255, 255, 255));
    lblBType.setText("Book Type");

    lblBAuthro.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
    lblBAuthro.setForeground(new java.awt.Color(255, 255, 255));
    lblBAuthro.setText("Author");

    lblBName.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
    lblBName.setForeground(new java.awt.Color(255, 255, 255));
    lblBName.setText("Book Name");

    txtBQuantity.setBackground(new java.awt.Color(44, 62, 80));
    txtBQuantity.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
    txtBQuantity.setForeground(new java.awt.Color(255, 255, 255));
    txtBQuantity.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

    lblBType1.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
    lblBType1.setForeground(new java.awt.Color(255, 255, 255));
    lblBType1.setText("Quantity");

    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblBName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblBAuthro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(33, 33, 33)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtBName, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addComponent(lblBType, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33)
                    .addComponent(txtBType, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addComponent(lblBType1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33)
                    .addComponent(txtBQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(26, 26, 26)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(btnBRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnBCancal, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                    .addComponent(btnBNew, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnBUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnBClear, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(42, 42, 42))
        .addGroup(jPanel7Layout.createSequentialGroup()
            .addGap(169, 169, 169)
            .addComponent(txtSearchBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
            .addContainerGap(18, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );
    jPanel7Layout.setVerticalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel7Layout.createSequentialGroup()
            .addComponent(txtSearchBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(12, 12, 12)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(23, 23, 23)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtBName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblBName))
            .addGap(7, 7, 7)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBAuthro)
                        .addComponent(txtBAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBType))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBType1)))
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBNew)
                        .addComponent(btnBUpdate)
                        .addComponent(btnBClear))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBRemove)
                        .addComponent(btnBCancal))))
            .addContainerGap(21, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout panWinBooksLayout = new javax.swing.GroupLayout(panWinBooks);
    panWinBooks.setLayout(panWinBooksLayout);
    panWinBooksLayout.setHorizontalGroup(
        panWinBooksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panWinBooksLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(panWinBooksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(39, Short.MAX_VALUE))
    );
    panWinBooksLayout.setVerticalGroup(
        panWinBooksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panWinBooksLayout.createSequentialGroup()
            .addGap(12, 12, 12)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(24, 24, 24)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    panWinReaders.setBackground(new java.awt.Color(20, 77, 122));
    panWinReaders.setForeground(new java.awt.Color(153, 255, 153));
    panWinReaders.setName(""); // NOI18N
    panWinReaders.setPreferredSize(new java.awt.Dimension(832, 537));

    jPanel1.setBackground(new java.awt.Color(0, 153, 204));
    jPanel1.setPreferredSize(new java.awt.Dimension(494, 68));
    jPanel1.setRequestFocusEnabled(false);

    jLabel6.setBackground(new java.awt.Color(0, 153, 255));
    jLabel6.setFont(new java.awt.Font("Poppins SemiBold", 1, 36)); // NOI18N
    jLabel6.setForeground(new java.awt.Color(255, 255, 255));
    jLabel6.setText("Readers");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(264, 264, 264)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(287, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap(15, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    jPanel8.setBackground(new java.awt.Color(20, 77, 122));
    jPanel8.setPreferredSize(new java.awt.Dimension(672, 78));

    txtSearchReaders.setBackground(new java.awt.Color(20, 77, 122));
    txtSearchReaders.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
    txtSearchReaders.setForeground(new java.awt.Color(255, 255, 255));
    txtSearchReaders.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Reader", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Poppins Light", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
    txtSearchReaders.setName("txtSearch"); // NOI18N
    txtSearchReaders.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtSearchReadersKeyReleased(evt);
        }
    });

    btnRCancal.setBackground(new java.awt.Color(26, 188, 156));
    btnRCancal.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    btnRCancal.setForeground(new java.awt.Color(0, 0, 0));
    btnRCancal.setText("Cancal");
    btnRCancal.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRCancalActionPerformed(evt);
        }
    });

    btnRNew.setBackground(new java.awt.Color(26, 188, 156));
    btnRNew.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    btnRNew.setForeground(new java.awt.Color(0, 0, 0));
    btnRNew.setText("New");
    btnRNew.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRNewActionPerformed(evt);
        }
    });

    btnRRemove.setBackground(new java.awt.Color(26, 188, 156));
    btnRRemove.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    btnRRemove.setForeground(new java.awt.Color(0, 0, 0));
    btnRRemove.setText("Remove");
    btnRRemove.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRRemoveActionPerformed(evt);
        }
    });

    btnRUpdate.setBackground(new java.awt.Color(26, 188, 156));
    btnRUpdate.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    btnRUpdate.setForeground(new java.awt.Color(0, 0, 0));
    btnRUpdate.setText("Update");
    btnRUpdate.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRUpdateActionPerformed(evt);
        }
    });

    btnRClear.setBackground(new java.awt.Color(26, 188, 156));
    btnRClear.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    btnRClear.setForeground(new java.awt.Color(0, 0, 0));
    btnRClear.setText("Clear");
    btnRClear.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRClearActionPerformed(evt);
        }
    });

    txtRAge.setBackground(new java.awt.Color(44, 62, 80));
    txtRAge.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
    txtRAge.setForeground(new java.awt.Color(255, 255, 255));
    txtRAge.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

    txtRName.setBackground(new java.awt.Color(44, 62, 80));
    txtRName.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
    txtRName.setForeground(new java.awt.Color(255, 255, 255));
    txtRName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

    txtRCity.setBackground(new java.awt.Color(44, 62, 80));
    txtRCity.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
    txtRCity.setForeground(new java.awt.Color(255, 255, 255));
    txtRCity.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

    lblRName.setBackground(new java.awt.Color(0, 0, 0));
    lblRName.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
    lblRName.setForeground(new java.awt.Color(255, 255, 255));
    lblRName.setText("Name");

    lblRAge.setBackground(new java.awt.Color(0, 0, 0));
    lblRAge.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
    lblRAge.setForeground(new java.awt.Color(255, 255, 255));
    lblRAge.setText("Age");

    lblRCity.setBackground(new java.awt.Color(0, 0, 0));
    lblRCity.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
    lblRCity.setForeground(new java.awt.Color(255, 255, 255));
    lblRCity.setText("City");

    tableReaders.setAutoCreateRowSorter(true);
    tableReaders.setBackground(new java.awt.Color(221, 221, 221));
    tableReaders.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
    tableReaders.setForeground(new java.awt.Color(0, 0, 0));
    tableReaders.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },
        new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }
    ));
    tableReaders.setMaximumSize(new java.awt.Dimension(0, 0));
    tableReaders.setMinimumSize(new java.awt.Dimension(0, 0));
    tableReaders.setSelectionBackground(new java.awt.Color(0, 255, 204));
    tableReaders.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tableReadersMouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(tableReaders);
    tableReaders.getAccessibleContext().setAccessibleName("");

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addGap(174, 174, 174)
            .addComponent(txtSearchReaders, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(165, Short.MAX_VALUE))
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane2)
            .addContainerGap())
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addComponent(lblRName, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)
                    .addComponent(txtRName, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblRAge, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRCity, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(txtRCity, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRAge, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGap(37, 37, 37)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(btnRRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnRCancal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addComponent(btnRNew, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnRUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnRClear, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(45, 45, 45))
    );
    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addComponent(txtSearchReaders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
            .addGap(28, 28, 28)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRName))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRAge))
                    .addGap(11, 11, 11)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtRCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRCity)))
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRNew)
                        .addComponent(btnRUpdate)
                        .addComponent(btnRClear))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRCancal)
                        .addComponent(btnRRemove))
                    .addGap(7, 7, 7)))
            .addGap(21, 21, 21))
    );

    javax.swing.GroupLayout panWinReadersLayout = new javax.swing.GroupLayout(panWinReaders);
    panWinReaders.setLayout(panWinReadersLayout);
    panWinReadersLayout.setHorizontalGroup(
        panWinReadersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panWinReadersLayout.createSequentialGroup()
            .addGap(21, 21, 21)
            .addGroup(panWinReadersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(10, Short.MAX_VALUE))
    );
    panWinReadersLayout.setVerticalGroup(
        panWinReadersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panWinReadersLayout.createSequentialGroup()
            .addGap(12, 12, 12)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    panWinStatus.setBackground(new java.awt.Color(20, 77, 122));
    panWinStatus.setPreferredSize(new java.awt.Dimension(832, 537));

    jPanel4.setBackground(new java.awt.Color(0, 153, 204));

    jLabel8.setFont(new java.awt.Font("Poppins ExtraBold", 1, 36)); // NOI18N
    jLabel8.setForeground(new java.awt.Color(255, 255, 255));
    jLabel8.setText("Status");

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
            .addContainerGap(297, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(263, 263, 263))
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
            .addContainerGap(10, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    jPanel5.setBackground(new java.awt.Color(0, 102, 153));

    jPanel6.setBackground(new java.awt.Color(0, 102, 153));
    jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    lblSBName.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    lblSBName.setForeground(new java.awt.Color(255, 255, 255));
    lblSBName.setText("Name :");

    lblSBAuthor.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    lblSBAuthor.setForeground(new java.awt.Color(255, 255, 255));
    lblSBAuthor.setText("Author :");

    lblSBType.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    lblSBType.setForeground(new java.awt.Color(255, 255, 255));
    lblSBType.setText("Type :");

    lblSBQuantity.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    lblSBQuantity.setForeground(new java.awt.Color(255, 255, 255));
    lblSBQuantity.setText("Quantity :");

    lblSBId.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    lblSBId.setForeground(new java.awt.Color(255, 255, 255));
    lblSBId.setText("Book ID :");

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addGap(19, 19, 19)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(lblSBQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSBType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSBAuthor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSBId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblSBName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(30, Short.MAX_VALUE))
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addGap(17, 17, 17)
            .addComponent(lblSBId)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lblSBName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lblSBAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lblSBType)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lblSBQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(17, Short.MAX_VALUE))
    );

    jPanel9.setBackground(new java.awt.Color(0, 102, 153));
    jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    lblSRId.setBackground(new java.awt.Color(0, 0, 0));
    lblSRId.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    lblSRId.setForeground(new java.awt.Color(255, 255, 255));
    lblSRId.setText("Reader ID :");

    lblSRAge.setBackground(new java.awt.Color(0, 0, 0));
    lblSRAge.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    lblSRAge.setForeground(new java.awt.Color(255, 255, 255));
    lblSRAge.setText("Age :");

    lblSRCity.setBackground(new java.awt.Color(0, 0, 0));
    lblSRCity.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    lblSRCity.setForeground(new java.awt.Color(255, 255, 255));
    lblSRCity.setText("City :");

    lblSRName.setBackground(new java.awt.Color(0, 0, 0));
    lblSRName.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    lblSRName.setForeground(new java.awt.Color(255, 255, 255));
    lblSRName.setText("Name :");

    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
    jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addGap(21, 21, 21)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblSRName, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addComponent(lblSRAge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSRCity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addComponent(lblSRId, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGap(27, 27, 27))
    );
    jPanel9Layout.setVerticalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSRId)
            .addGap(13, 13, 13)
            .addComponent(lblSRName)
            .addGap(11, 11, 11)
            .addComponent(lblSRAge)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(lblSRCity)
            .addGap(21, 21, 21))
    );

    selecDateIssue.setFormatoFecha("yyyy-MM-dd");
    selecDateIssue.setPlaceholder("Issue Date");

    selecDateReturn.setFormatoFecha("yyyy-MM-dd");
    selecDateReturn.setPlaceholder("Returen Date");

    btnIBook.setBackground(new java.awt.Color(26, 188, 156));
    btnIBook.setForeground(new java.awt.Color(0, 0, 0));
    btnIBook.setText("Issue Book");
    btnIBook.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnIBookActionPerformed(evt);
        }
    });

    lblSBQuantity1.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
    lblSBQuantity1.setForeground(new java.awt.Color(255, 255, 255));
    lblSBQuantity1.setText("Return Date:");

    lblSBQuantity2.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
    lblSBQuantity2.setForeground(new java.awt.Color(255, 255, 255));
    lblSBQuantity2.setText("Issue Date:");

    txtSBId.setBackground(new java.awt.Color(0, 102, 153));
    txtSBId.setForeground(new java.awt.Color(255, 255, 255));
    txtSBId.setToolTipText("Enter Book ID");
    txtSBId.setBorderColor(new java.awt.Color(255, 255, 255));
    txtSBId.setDisabledTextColor(new java.awt.Color(255, 255, 255));
    txtSBId.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    txtSBId.setMargin(new java.awt.Insets(1, 1, 1, 1));
    txtSBId.setMinimumSize(new java.awt.Dimension(1, 1));
    txtSBId.setPhColor(new java.awt.Color(255, 255, 255));
    txtSBId.setPlaceholder(" Enter Book ID");
    txtSBId.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseExited(java.awt.event.MouseEvent evt) {
            txtSBIdMouseExited(evt);
        }
    });

    txtSRId.setBackground(new java.awt.Color(0, 102, 153));
    txtSRId.setForeground(new java.awt.Color(255, 255, 255));
    txtSRId.setBorderColor(new java.awt.Color(255, 255, 255));
    txtSRId.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    txtSRId.setPhColor(new java.awt.Color(255, 255, 255));
    txtSRId.setPlaceholder(" Enter Reader ID");
    txtSRId.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseExited(java.awt.event.MouseEvent evt) {
            txtSRIdMouseExited(evt);
        }
    });

    jLabel1.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setText("Enter Book ID :");

    jLabel2.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setText("Enter Reader ID :");

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
            .addContainerGap(16, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(24, 24, 24))
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addGap(69, 69, 69)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtSBId, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSRId, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(80, 80, 80))
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(391, 391, 391)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(224, 224, 224)
                    .addComponent(btnIBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(88, 88, 88)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblSBQuantity2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(selecDateIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(51, 51, 51)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(selecDateReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSBQuantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addGap(30, 30, 30)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtSBId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSRId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(26, 26, 26)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(lblSBQuantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblSBQuantity2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(selecDateIssue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(selecDateReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(37, 37, 37)
            .addComponent(btnIBook, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(35, 35, 35))
    );

    javax.swing.GroupLayout panWinStatusLayout = new javax.swing.GroupLayout(panWinStatus);
    panWinStatus.setLayout(panWinStatusLayout);
    panWinStatusLayout.setHorizontalGroup(
        panWinStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panWinStatusLayout.createSequentialGroup()
            .addContainerGap(22, Short.MAX_VALUE)
            .addGroup(panWinStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );
    panWinStatusLayout.setVerticalGroup(
        panWinStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panWinStatusLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );

    panWinAnalysis.setBackground(new java.awt.Color(20, 77, 122));
    panWinAnalysis.setPreferredSize(new java.awt.Dimension(832, 537));

    jPanel3.setBackground(new java.awt.Color(0, 153, 204));

    jLabel7.setFont(new java.awt.Font("Poppins ExtraBold", 1, 36)); // NOI18N
    jLabel7.setForeground(new java.awt.Color(255, 255, 255));
    jLabel7.setText("Analysis");

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addContainerGap(262, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(301, 301, 301))
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    jPanel10.setBackground(new java.awt.Color(20, 77, 122));

    tableIssue.setAutoCreateRowSorter(true);
    tableIssue.setBackground(new java.awt.Color(221, 221, 221));
    tableIssue.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
    tableIssue.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null}
        },
        new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
        }
    ));
    tableIssue.setSelectionBackground(new java.awt.Color(0, 255, 204));
    jScrollPane3.setViewportView(tableIssue);

    lblIssued.setBackground(new java.awt.Color(26, 188, 156));

    lblTotIssued.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library_MS/issue.gif"))); // NOI18N

    javax.swing.GroupLayout lblIssuedLayout = new javax.swing.GroupLayout(lblIssued);
    lblIssued.setLayout(lblIssuedLayout);
    lblIssuedLayout.setHorizontalGroup(
        lblIssuedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(lblIssuedLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(lblTotIssued, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addContainerGap())
    );
    lblIssuedLayout.setVerticalGroup(
        lblIssuedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(lblIssuedLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(lblTotIssued, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );

    jPanel12.setBackground(new java.awt.Color(26, 188, 156));

    lblTotReaders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library_MS/readers.gif"))); // NOI18N

    javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
    jPanel12.setLayout(jPanel12Layout);
    jPanel12Layout.setHorizontalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel12Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(lblTotReaders, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanel12Layout.setVerticalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(lblTotReaders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );

    jPanel13.setBackground(new java.awt.Color(26, 188, 156));

    lblTotBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library_MS/books.gif"))); // NOI18N

    javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
    jPanel13.setLayout(jPanel13Layout);
    jPanel13Layout.setHorizontalGroup(
        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel13Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(lblTotBooks, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanel13Layout.setVerticalGroup(
        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel13Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(lblTotBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    btnRBook.setBackground(new java.awt.Color(26, 188, 156));
    btnRBook.setForeground(new java.awt.Color(0, 0, 0));
    btnRBook.setText("Return Book");
    btnRBook.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRBookActionPerformed(evt);
        }
    });

    txtIssue.setBackground(new java.awt.Color(20, 77, 122));
    txtIssue.setForeground(new java.awt.Color(255, 255, 255));
    txtIssue.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Issued", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Poppins Medium", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
    txtIssue.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtIssueActionPerformed(evt);
        }
    });
    txtIssue.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtIssueKeyReleased(evt);
        }
    });

    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
    jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblIssued, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(158, 158, 158)
                    .addComponent(txtIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addGap(199, 199, 199)
            .addComponent(btnRBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel10Layout.setVerticalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
            .addGap(31, 31, 31)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblIssued, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(txtIssue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnRBook, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(14, 14, 14))
    );

    javax.swing.GroupLayout panWinAnalysisLayout = new javax.swing.GroupLayout(panWinAnalysis);
    panWinAnalysis.setLayout(panWinAnalysisLayout);
    panWinAnalysisLayout.setHorizontalGroup(
        panWinAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panWinAnalysisLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(panWinAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panWinAnalysisLayout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(18, Short.MAX_VALUE))
    );
    panWinAnalysisLayout.setVerticalGroup(
        panWinAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panWinAnalysisLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(28, 28, 28)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(panMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 783, Short.MAX_VALUE))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 226, Short.MAX_VALUE)
                .addComponent(panWinStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 226, Short.MAX_VALUE)
                .addComponent(panWinBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 227, Short.MAX_VALUE)
                .addComponent(panWinAnalysis, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 227, Short.MAX_VALUE)
                .addComponent(panWinReaders, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(panMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panWinStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panWinBooks, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panWinAnalysis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panWinReaders, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
    );

    pack();
    setLocationRelativeTo(null);
}// </editor-fold>//GEN-END:initComponents

//Funcions Calling Actions
private void panTabBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panTabBooksMouseClicked

    books();
    bookTableData();
}//GEN-LAST:event_panTabBooksMouseClicked
private void panTabReadersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panTabReadersMouseClicked
    readers();
    readerTableData();
}//GEN-LAST:event_panTabReadersMouseClicked
private void panTabAnalysisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panTabAnalysisMouseClicked
    analysis();
    issueTableData();
    details();
    String gifLocation1 = "D:\\Projects\\Java\\Library Management System\\src\\Library_MS\\books.gif"; // Replace with your actual GIF location
    ImageIcon icon1 = new ImageIcon(gifLocation1);
    String gifLocation2 = "D:\\Projects\\Java\\Library Management System\\src\\Library_MS\\readers.gif"; // Replace with your actual GIF location
    ImageIcon icon2 = new ImageIcon(gifLocation2);
    String gifLocation3 = "D:\\Projects\\Java\\Library Management System\\src\\Library_MS\\issue.gif"; // Replace with your actual GIF location
    ImageIcon icon3 = new ImageIcon(gifLocation3);

    lblTotBooks.setIcon(icon1);
    lblTotReaders.setIcon(icon2);
    lblTotIssued.setIcon(icon3);
    jPanel13.revalidate();
    jPanel13.repaint();
}//GEN-LAST:event_panTabAnalysisMouseClicked
private void panTabBooksMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panTabBooksMouseEntered
    tabEnterBooks();
}//GEN-LAST:event_panTabBooksMouseEntered
private void panTabReadersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panTabReadersMouseEntered
    tabEnterReaders();
}//GEN-LAST:event_panTabReadersMouseEntered
private void panTabAnalysisMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panTabAnalysisMouseEntered
    tabEnterAnalysis();
}//GEN-LAST:event_panTabAnalysisMouseEntered
private void panTabBooksMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panTabBooksMouseExited
    tabExitBooks();
}//GEN-LAST:event_panTabBooksMouseExited
private void panTabReadersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panTabReadersMouseExited
    tabExitReaders();
}//GEN-LAST:event_panTabReadersMouseExited
private void panTabAnalysisMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panTabAnalysisMouseExited
    tabExitAnalysis();
}//GEN-LAST:event_panTabAnalysisMouseExited
private void btnBCancalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBCancalActionPerformed
    System.exit(0);
}//GEN-LAST:event_btnBCancalActionPerformed
private void btnBNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBNewActionPerformed
    try {
        newBookData();

    } catch (SQLException ex) {
        Logger.getLogger(manage.class
                .getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnBNewActionPerformed
private void txtSearchBooksKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchBooksKeyReleased
    try {
        SearchBooks();

    } catch (SQLException ex) {
        Logger.getLogger(manage.class
                .getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_txtSearchBooksKeyReleased
private void btnBClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBClearActionPerformed
    clearBData();
}//GEN-LAST:event_btnBClearActionPerformed
private void btnBUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBUpdateActionPerformed
    updateBData();
    bookTableData();
}//GEN-LAST:event_btnBUpdateActionPerformed
private void btnBRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBRemoveActionPerformed

    removeBData();
    bookTableData();
}//GEN-LAST:event_btnBRemoveActionPerformed

private void txtSearchReadersKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchReadersKeyReleased
    try {
        SearchReaders();

    } catch (SQLException ex) {
        Logger.getLogger(manage.class
                .getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_txtSearchReadersKeyReleased
private void tableBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBooksMouseClicked
    backToBTxt();
}//GEN-LAST:event_tableBooksMouseClicked

private void panTabStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panTabStatusMouseClicked
    status();
}//GEN-LAST:event_panTabStatusMouseClicked
private void panTabStatusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panTabStatusMouseEntered
    tabEnterStatus();
}//GEN-LAST:event_panTabStatusMouseEntered
private void panTabStatusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panTabStatusMouseExited
    tabExitStatus();    }//GEN-LAST:event_panTabStatusMouseExited

private void btnRClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRClearActionPerformed
    clearRData();
}//GEN-LAST:event_btnRClearActionPerformed
private void btnRUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRUpdateActionPerformed
    updateRData();
    readerTableData();
}//GEN-LAST:event_btnRUpdateActionPerformed
private void btnRRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRRemoveActionPerformed

    removeRData();
    readerTableData();
}//GEN-LAST:event_btnRRemoveActionPerformed
private void btnRNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRNewActionPerformed
    try {
        newReaderData();

    } catch (SQLException ex) {
        Logger.getLogger(manage.class
                .getName()).log(Level.SEVERE, null, ex);
    }
    readerTableData();
}//GEN-LAST:event_btnRNewActionPerformed
private void btnRCancalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRCancalActionPerformed
    System.exit(0);
}//GEN-LAST:event_btnRCancalActionPerformed

private void tableReadersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableReadersMouseClicked
    backToRTxt();
}//GEN-LAST:event_tableReadersMouseClicked
private void txtSBIdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSBIdMouseExited
    statusBookData();
}//GEN-LAST:event_txtSBIdMouseExited
private void txtSRIdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSRIdMouseExited
    statusReaderData();
}//GEN-LAST:event_txtSRIdMouseExited

private void btnIBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIBookActionPerformed

    try {
        issueBook();
    } catch (SQLException ex) {
        Logger.getLogger(manage.class.getName()).log(Level.SEVERE, null, ex);
    }
    statusBookData();
    statusReaderData();
}//GEN-LAST:event_btnIBookActionPerformed
private void txtIssueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIssueKeyReleased
    SearchIssued();
}//GEN-LAST:event_txtIssueKeyReleased
private void btnRBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRBookActionPerformed

    returnBook();
    statusBookData();
}//GEN-LAST:event_btnRBookActionPerformed

private void txtIssueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIssueActionPerformed
    
}//GEN-LAST:event_txtIssueActionPerformed


public static void main(String args[]) {

    java.awt.EventQueue.invokeLater(() -> {
        new manage().setVisible(true);
    });
}


// Variables declaration - do not modify//GEN-BEGIN:variables
private javax.swing.JButton btnBCancal;
private javax.swing.JButton btnBClear;
private javax.swing.JButton btnBNew;
private javax.swing.JButton btnBRemove;
private javax.swing.JButton btnBUpdate;
private rojerusan.RSMaterialButtonRectangle btnIBook;
private rojerusan.RSMaterialButtonRectangle btnRBook;
private javax.swing.JButton btnRCancal;
private javax.swing.JButton btnRClear;
private javax.swing.JButton btnRNew;
private javax.swing.JButton btnRRemove;
private javax.swing.JButton btnRUpdate;
private javax.swing.JLabel jLabel1;
private javax.swing.JLabel jLabel2;
private javax.swing.JLabel jLabel6;
private javax.swing.JLabel jLabel7;
private javax.swing.JLabel jLabel8;
private javax.swing.JLabel jLabel9;
private javax.swing.JPanel jPanel1;
private javax.swing.JPanel jPanel10;
private javax.swing.JPanel jPanel12;
private javax.swing.JPanel jPanel13;
private javax.swing.JPanel jPanel2;
private javax.swing.JPanel jPanel3;
private javax.swing.JPanel jPanel4;
private javax.swing.JPanel jPanel5;
private javax.swing.JPanel jPanel6;
private javax.swing.JPanel jPanel7;
private javax.swing.JPanel jPanel8;
private javax.swing.JPanel jPanel9;
private javax.swing.JScrollPane jScrollPane1;
private javax.swing.JScrollPane jScrollPane2;
private javax.swing.JScrollPane jScrollPane3;
private javax.swing.JLabel lblAnalysis;
private javax.swing.JLabel lblBAuthro;
private javax.swing.JLabel lblBName;
private javax.swing.JLabel lblBType;
private javax.swing.JLabel lblBType1;
private javax.swing.JLabel lblBooks;
private javax.swing.JPanel lblIssued;
private javax.swing.JLabel lblRAge;
private javax.swing.JLabel lblRCity;
private javax.swing.JLabel lblRName;
private javax.swing.JLabel lblReaders;
private javax.swing.JLabel lblSBAuthor;
private javax.swing.JLabel lblSBId;
private javax.swing.JLabel lblSBName;
private javax.swing.JLabel lblSBQuantity;
private javax.swing.JLabel lblSBQuantity1;
private javax.swing.JLabel lblSBQuantity2;
private javax.swing.JLabel lblSBType;
private javax.swing.JLabel lblSRAge;
private javax.swing.JLabel lblSRCity;
private javax.swing.JLabel lblSRId;
private javax.swing.JLabel lblSRName;
private javax.swing.JLabel lblStatus;
private javax.swing.JLabel lblTotBooks;
private javax.swing.JLabel lblTotIssued;
private javax.swing.JLabel lblTotReaders;
private javax.swing.JPanel panMenu;
private javax.swing.JPanel panTabAnalysis;
private javax.swing.JPanel panTabBooks;
private javax.swing.JPanel panTabReaders;
private javax.swing.JPanel panTabStatus;
private javax.swing.JPanel panWinAnalysis;
private javax.swing.JPanel panWinBooks;
private javax.swing.JPanel panWinReaders;
private javax.swing.JPanel panWinStatus;
private rojeru_san.componentes.RSDateChooser selecDateIssue;
private rojeru_san.componentes.RSDateChooser selecDateReturn;
private javax.swing.JTable tableBooks;
private javax.swing.JTable tableIssue;
private javax.swing.JTable tableReaders;
private javax.swing.JTextField txtBAuthor;
private javax.swing.JTextField txtBName;
private javax.swing.JTextField txtBQuantity;
private javax.swing.JTextField txtBType;
private javax.swing.JTextField txtIssue;
private javax.swing.JTextField txtRAge;
private javax.swing.JTextField txtRCity;
private javax.swing.JTextField txtRName;
private rojerusan.RSMetroTextPlaceHolder txtSBId;
private rojerusan.RSMetroTextPlaceHolder txtSRId;
private javax.swing.JTextField txtSearchBooks;
private javax.swing.JTextField txtSearchReaders;
// End of variables declaration//GEN-END:variables
}

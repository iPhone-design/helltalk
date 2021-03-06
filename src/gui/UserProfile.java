package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import library.ImageFile;
import library.ObjectInOut;
import library.User;

public class UserProfile extends JDialog implements WindowListener {
	private JPanel contentPanel = new JPanel();
	private JPasswordField tfd_pw;
	private JPasswordField tfd_confirm_pw;
	private JTextField tfd_id;
	private JTextField tfd_nickName;
	private ImageIcon btnImage;
	private JLabel lbl_mainNickName;
	private File imageFile;
	private List<JTextField> list = new ArrayList();
	private User user;
	private JLabel lbl_id;
	private JLabel lbl_pw;
	private JLabel lbl_nickName;
	private JLabel lbl_confirm_pw;
	private MainFrame mainFrame;
	private String id;
	private String nicName;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ObjectInOut object;
	
	public UserProfile(ObjectOutputStream oos, ObjectInputStream ois, MainFrame mainFrame, String id, String nicName) {
		this.oos = oos;
		this.ois = ois;
		this.mainFrame = mainFrame;
		this.id = id;
		this.nicName = nicName;
		
		setBounds(100, 100, 360, 490);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(mainFrame);
		setModal(true);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 228, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 320, 359);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		lbl_mainNickName = new JLabel(nicName);
		lbl_mainNickName.setBounds(85, 167, 170, 47);
		panel.add(lbl_mainNickName);
		lbl_mainNickName.setFont(new Font("?????? ??????", Font.BOLD, 21));
		lbl_mainNickName.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfd_pw = new JPasswordField();
		tfd_pw.setColumns(10);
		tfd_pw.setBounds(121, 283, 170, 21);
		panel.add(tfd_pw);
		
		tfd_confirm_pw = new JPasswordField();
		tfd_confirm_pw.setColumns(10);
		tfd_confirm_pw.setBounds(121, 314, 170, 21);
		panel.add(tfd_confirm_pw);
		
		lbl_id = new JLabel("??????");
		lbl_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id.setFont(new Font("?????? ??????", Font.BOLD, 13));
		lbl_id.setBounds(21, 253, 98, 15);
		panel.add(lbl_id);
		
		tfd_id = new JTextField(id);
		tfd_id.setColumns(10);
		tfd_id.setEditable(false);
		tfd_id.setBounds(121, 252, 170, 21);
		panel.add(tfd_id);
		
		lbl_pw = new JLabel("????????????");
		lbl_pw.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pw.setFont(new Font("?????? ??????", Font.BOLD, 13));
		lbl_pw.setBounds(21, 284, 97, 15);
		panel.add(lbl_pw);
		
		tfd_nickName = new JTextField(nicName);
		tfd_nickName.setColumns(10);
		tfd_nickName.setBounds(121, 223, 170, 21);
		panel.add(tfd_nickName);
		
		// ???????????? ??????
		list.add(tfd_nickName);
		list.add(tfd_pw);
		list.add(tfd_confirm_pw);
		
		lbl_nickName = new JLabel("?????????");
		lbl_nickName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nickName.setFont(new Font("?????? ??????", Font.BOLD, 13));
		lbl_nickName.setBounds(22, 224, 97, 15);
		panel.add(lbl_nickName);
		
		JButton btn_profile = new JButton("????????? ?????????");
		btn_profile.setBounds(96, 31, 151, 138);
		try {
			object = new ObjectInOut(ObjectInOut.IMAGELOAD, id);
			oos.writeObject(object);
			oos.flush();
			object = (ObjectInOut) ois.readObject();
			imageFile = new File(".\\img\\" + object.getFileName());
			btnImage = new ImageIcon(imageFile.getPath());
			btn_profile.setIcon(btnImage);
			panel.add(btn_profile);
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		lbl_confirm_pw = new JLabel("???????????? ??????");
		lbl_confirm_pw.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_confirm_pw.setFont(new Font("?????? ??????", Font.BOLD, 13));
		lbl_confirm_pw.setBounds(21, 316, 88, 15);
		panel.add(lbl_confirm_pw);
		
		JButton imageFileButton = new JButton("???????????????");
		imageFileButton.setBounds(12, 382, 102, 59);
		imageFileButton.setFont(new Font("?????? ??????", Font.BOLD, 13));
		contentPanel.add(imageFileButton);
		
		JButton infoChangeButton = new JButton("????????????");
		infoChangeButton.setBounds(135, 382, 89, 59);
		infoChangeButton.setFont(new Font("?????? ??????", Font.BOLD, 13));
		contentPanel.add(infoChangeButton);
		
		JButton userLeave = new JButton("????????????");
		userLeave.setFont(new Font("?????? ??????", Font.BOLD, 13));
		userLeave.setBounds(242, 382, 90, 59);
		contentPanel.add(userLeave);
		
		addWindowListener(this);
		
		// ?????? ??????
		infoChangeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "";
				// ?????? & ?????? ?????????
				if (checkData() == 1) {
					message = "????????? ???????????????.";
					showMessage("Error", message);
				} else if (checkData() == 2) {
					message = "????????? ??????????????????.";
					showMessage("Error", message);
				} else if (!checkPassword()) {
					message = "??????????????? ???????????? ????????????.";
					showMessage("Error", message);
				} else {
					try {
						object = new ObjectInOut(ObjectInOut.INFOCHANGE, tfd_id.getText(), getPassword(tfd_pw.getPassword()), 0, tfd_nickName.getText());
						oos.writeObject(object);
						oos.flush();
						object = (ObjectInOut) ois.readObject();
						if (object.getProtocol() == ObjectInOut.INFOCHANGE) {
							if (object.getResult() == 1) {
								message = "?????? ?????? ??? ????????? ????????? ?????????.!";
								showMessage("Success", message);
								dispose();
								mainFrame.changeLoginPanel();
							} else if(object.getResult() == -1){
								message = "?????? ??????";
								showMessage("Fail", message);
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		// ?????? ??????
		userLeave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					object = new ObjectInOut(ObjectInOut.USERLEAVE, id);
					oos.writeObject(object);
					oos.flush();
					object = (ObjectInOut) ois.readObject();
					if (object.getProtocol() == ObjectInOut.USERLEAVE && object.getResult() == 1) {
						showMessage("????????????", "?????? ??????");
						dispose();
						mainFrame.changeFirstPanel();
					} else if (object.getProtocol() == ObjectInOut.USERLEAVE && object.getResult() == -1) {
						showMessage("????????????", "?????? ??????");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		// ????????? ??????
		btn_profile.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            JDialog profileImage = new JDialog();
	            profileImage.setLocationRelativeTo(mainFrame);
	            JLabel jlabel = new JLabel();
	            jlabel.setIcon(btnImage);
	            profileImage.getContentPane().add(jlabel);
	            profileImage.setModal(true);
	            profileImage.pack();
	            profileImage.setLocationRelativeTo(null);
	            profileImage.setResizable(false);
	            profileImage.setVisible(true);
	         }
	    });
		
		// ????????? ??????
		imageFileButton.addActionListener(new ActionListener() {
			private BufferedImage bufferedImage;
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(".\\default_img\\");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("????????? ??????", "png", "jpg", "jpeg", "gif");
				chooser.addChoosableFileFilter(filter);
				chooser.setAcceptAllFileFilterUsed(false);
				int i = chooser.showOpenDialog(null);
				FileInputStream fis;
				File filePath;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				if (i == JFileChooser.APPROVE_OPTION) {
					try {
						filePath = new File(chooser.getSelectedFile().getPath());
						imageFile = new File(chooser.getSelectedFile().getPath());
						btn_profile.setIcon(new ImageIcon(imageFile.getPath()));
						btnImage = new ImageIcon(imageFile.getPath());
						fis = new FileInputStream(filePath);
						int len = 0;
						byte[] buf = new byte[(int) filePath.length()];
						while ((len = fis.read(buf)) != -1) {
							baos.write(buf, 0, len);
						}
						byte[] fileArray = baos.toByteArray();
						object = new ObjectInOut(ObjectInOut.IMAGECHANGE, id, chooser.getSelectedFile().getName(), fileArray);
						oos.writeObject(object);
						oos.flush();
						object = (ObjectInOut) ois.readObject();
						ImageFile imageFile = object.getImageFile();
						FileOutputStream fos = new FileOutputStream(new File(".\\img\\" + imageFile.getImageName()));
						fos.write(imageFile.getImageByte());
						showMessage("????????? ??????", "????????? ?????? ??????!");
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	// ???????????? String ?????? ?????????
	private String getPassword(char[] pw) {
		String password = "";
		for (char p : pw) {
			Character.toString(p);
			password += p;
		}
		System.out.println(password);
		return password;
	}
	
	// ????????? ?????? ?????? ?????? ?????????
	public int checkData() {
		for (int i = 0; i < 3; i++) {
			if (list.get(i).getText().equals("")) { 			// ?????? ??????
				return 1;
			} else if (list.get(i).getText().contains(" ")) {	// ?????? ??????
				return 2;
			}
		}
		return 0;
	}
	
	// ???????????? ?????? ?????? ?????? ?????????
	public boolean checkPassword() {
		String pw = getPassword(tfd_pw.getPassword());
		String conPw = getPassword(tfd_confirm_pw.getPassword());
		if (pw.equals(conPw)) {
			return true;
		}
		return false;
	}
	
	// ????????? ????????? ?????????
	public void showMessage(String type, String message) {
		JOptionPane.showMessageDialog(null, message, type, JOptionPane.WARNING_MESSAGE);
	}
	
	public User getUserInfo() {
		user = new User(tfd_id.getText()
				, getPassword(tfd_pw.getPassword())
				, tfd_nickName.getText());
		return user;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		mainFrame.setStop(true);
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
 		mainFrame.setStop(true);
	}
}
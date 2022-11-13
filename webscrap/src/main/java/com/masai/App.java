package com.masai;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
	
	public static String getData(String country) throws Exception {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html>"+"<body style='text-align:center;color:red;'>"
		);
		buffer.append(country.toUpperCase()+"<br>");
		String url = "https://www.worldometers.info/coronavirus/country/"+country+"/";
		Document doc =  Jsoup.connect(url).get();
		System.out.println(doc.title());
//	maincounter-wrap
	 Elements elements = doc.select("#maincounter-wrap");
	
	 
	 elements.forEach(e->{
	String text = 	e.select("h1").text();
	String count = 	e.select(".maincounter-number>span").text();
	
//	System.out.println(text);
//	System.out.println();
//	System.out.println(count);
	buffer.append(text).append(count).append("<br>");
	 
	 });
	 buffer.append("<body>"+"</html>");
	  return buffer.toString();
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//          Scanner sc = new Scanner(System.in);
//          System.out.println("Enter country");
//          String con = sc.next();
//		String res =  getData(con);
//		System.out.println(res);
		
		
//		creating GUI
		JFrame root = new JFrame("Details of country");
		root.setSize(500,500);
		
		  Font f = new Font("Poppins",Font.BOLD, 30);
		  
//		  textfield
		  JTextField field = new JTextField();
//		  label
		  JLabel dataL = new JLabel();
		  
		  field.setFont(f);
		  dataL.setFont(f);
		 field.setHorizontalAlignment(SwingConstants.CENTER);
		 dataL.setHorizontalAlignment(SwingConstants.CENTER);
		  
		  JButton btn = new JButton("Get");
		  btn.addActionListener(event->{
			
			 try {
				   String mainData = field.getText();
				String originalData =  getData(mainData);
				dataL.setText(originalData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  });
		  btn.setFont(f);
		  
		  root.setLayout(new BorderLayout());
		  root.add(field,BorderLayout.NORTH);
		  root.add(dataL, BorderLayout.CENTER);
		  root.add(btn, BorderLayout.SOUTH);
		  root.setVisible(true);
	}

}

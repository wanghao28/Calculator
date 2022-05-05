package cn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

/**
 * Create by Intellij IDEA
 * User: wanghao
 * Date: 2022/5/5
 */
public class Calculator {
    public boolean isNumber(String value){
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+[\\.]?[\\d]*$");
        return pattern.matcher(value).matches();
    }
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        boolean isCount = false;
        //��������
        JFrame jframe=new JFrame("������");
        jframe.setLocation(200,300);//����λ��
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //������壬�߽粼��
        BorderLayout borderLayout = new BorderLayout();
        JPanel jPanel=new JPanel();
        jPanel.setLayout(borderLayout);
        //������ʾ�ı���
        JTextField jTextField=new JTextField("0",30);
        jTextField.setHorizontalAlignment(JTextField.RIGHT);
        jTextField.setEditable(false);
        jPanel.add(jTextField,BorderLayout.NORTH);
        //���ð�ť����
        JPanel jPanelSouth=new JPanel();
        jPanel.add(jPanelSouth,BorderLayout.SOUTH);
        //��ť����Ϊ���񲼾�
        GridLayout gridLayout = new GridLayout(5,4);
        jPanelSouth.setLayout(gridLayout);
        String[] buttonArray={"1","2","3","+","4","5","6","-","7","8","9","*",".","0","���","/","%","ȡ��","AC","="};
        for(int i=0; i<buttonArray.length; i++){
            //���ð�ť�ͼ�����
            JButton jButton = new JButton(buttonArray[i]);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String jTextFieldText = jTextField.getText();
                    String command = e.getActionCommand();//��ȡ��ť������
                    if(command.equals("AC")){
                        jTextField.setText("0");
                    }else if(command.equals("���")){
                        if(calculator.isNumber(jTextFieldText)){
                            jTextField.setText(String.valueOf(Math.sqrt(Double.valueOf(jTextFieldText))));
                        }
                    }else if(command.equals("ȡ��")){
                        if(calculator.isNumber(jTextFieldText)){
                            jTextField.setText(String.valueOf(-Double.valueOf(jTextFieldText)));
                        }
                    }else if(command.equals("=")){
                        if(!calculator.isNumber(jTextFieldText)){
                            try {
                                ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
                                jTextField.setText(String.valueOf(scriptEngine.eval(jTextFieldText)));
                            } catch (ScriptException exception) {
                                exception.printStackTrace();
                            }
                        }
                    }else{
                        if(jTextFieldText.equals("0") && Pattern.compile("[0-9]?").matcher(command).matches()){
                            jTextField.setText(command);
                        }else{
                            jTextField.setText(jTextFieldText+command);
                        }
                    }
                }
            });
            jPanelSouth.add(jButton);
        }
        jframe.add(jPanel);
        jframe.pack();
    }
}

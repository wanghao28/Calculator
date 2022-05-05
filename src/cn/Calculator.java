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
        //创建窗口
        JFrame jframe=new JFrame("计算器");
        jframe.setLocation(200,300);//设置位置
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置面板，边界布局
        BorderLayout borderLayout = new BorderLayout();
        JPanel jPanel=new JPanel();
        jPanel.setLayout(borderLayout);
        //设置显示文本域
        JTextField jTextField=new JTextField("0",30);
        jTextField.setHorizontalAlignment(JTextField.RIGHT);
        jTextField.setEditable(false);
        jPanel.add(jTextField,BorderLayout.NORTH);
        //设置按钮区域
        JPanel jPanelSouth=new JPanel();
        jPanel.add(jPanelSouth,BorderLayout.SOUTH);
        //按钮区域为网格布局
        GridLayout gridLayout = new GridLayout(5,4);
        jPanelSouth.setLayout(gridLayout);
        String[] buttonArray={"1","2","3","+","4","5","6","-","7","8","9","*",".","0","求根","/","%","取反","AC","="};
        for(int i=0; i<buttonArray.length; i++){
            //配置按钮和监听器
            JButton jButton = new JButton(buttonArray[i]);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String jTextFieldText = jTextField.getText();
                    String command = e.getActionCommand();//获取按钮的内容
                    if(command.equals("AC")){
                        jTextField.setText("0");
                    }else if(command.equals("求根")){
                        if(calculator.isNumber(jTextFieldText)){
                            jTextField.setText(String.valueOf(Math.sqrt(Double.valueOf(jTextFieldText))));
                        }
                    }else if(command.equals("取反")){
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

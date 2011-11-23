using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace practice2_GUI
{
    public partial class Form1 : Form
    {

        const int ADD = 1;
        const int SUB = 3;
        const int DIV = 0;
        const int MUDL = 4;
        const int MUL = 2;

        DataTable m_dataTable;
            

        public Form1()
        {
            m_dataTable =  CreateTable();
            InitializeComponent();
            historyGridView.DataSource  = m_dataTable;
        }

        private DataTable CreateTable()
        {
            DataTable t = new DataTable("Action history");
            t.Columns.Add("Num1", typeof(int));
            t.Columns.Add("Action",typeof(string));
            t.Columns.Add("Num2" , typeof(int));
            
            return t;
        }

        private void AddToHistory(string result)
        {
            historyLlistBox.Items.Add(result);
        }


        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }

        private void computeButton_Click(object sender, EventArgs e)
        {
           string resultStr = String.Empty;
           int action = -1 ;
           int num1 = 0;
           int num2 = 0;
            
            if (!string.IsNullOrEmpty(FristNumtextBox.Text) && !string.IsNullOrEmpty(SecondNumtextBox.Text))
            {
                try
                {

                   
                action = ActioncomboBox.SelectedIndex;
                num1 = Convert.ToInt32(FristNumtextBox.Text);
                num2 = Convert.ToInt32(SecondNumtextBox.Text);

                if (action != -1)
                    {
                        switch (action)
                        {
                            case MUDL:
                                
                                resultStr = (num1 % num2).ToString();
                                m_dataTable.Rows.Add(num1,  "%",num2);
                                break;
                            case DIV:
                                resultStr = (num1 / num2).ToString();
                                m_dataTable.Rows.Add(num1, "/", num2);
                                break;
                            case SUB:
                                resultStr = (num1 - num2).ToString();
                                m_dataTable.Rows.Add(num1, "-", num2);
                                break;
                            case MUL:
                                resultStr = (num1 * num2).ToString();
                                m_dataTable.Rows.Add(num1, "*", num2);
                                break;
                            case ADD:
                                resultStr = (num1 + num2).ToString();
                                m_dataTable.Rows.Add(num1, "+", num2);
                                break;
                        }
                        resultStr = "the result :" + resultStr;
                        AddToHistory(resultStr);
                        ResultTextBox.Text = resultStr;
                    }
                }


                catch (Exception)
                {
                    if (action == DIV && num2 == 0)
                        MessageBox.Show("you can't div in zero","Error", MessageBoxButtons.OK, MessageBoxIcon.Error);

                }

            }
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}

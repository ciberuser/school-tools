namespace practice2_GUI
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.ActioncomboBox = new System.Windows.Forms.ComboBox();
            this.FristNumtextBox = new System.Windows.Forms.TextBox();
            this.SecondNumtextBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.ResultTextBox = new System.Windows.Forms.TextBox();
            this.historyLlistBox = new System.Windows.Forms.ListBox();
            this.label3 = new System.Windows.Forms.Label();
            this.computeButton = new System.Windows.Forms.Button();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // ActioncomboBox
            // 
            this.ActioncomboBox.FormattingEnabled = true;
            this.ActioncomboBox.Items.AddRange(new object[] {
            "/",
            "+",
            "*",
            "-",
            "%"});
            this.ActioncomboBox.Location = new System.Drawing.Point(100, 37);
            this.ActioncomboBox.Name = "ActioncomboBox";
            this.ActioncomboBox.Size = new System.Drawing.Size(27, 21);
            this.ActioncomboBox.TabIndex = 0;
            // 
            // FristNumtextBox
            // 
            this.FristNumtextBox.Location = new System.Drawing.Point(12, 38);
            this.FristNumtextBox.Name = "FristNumtextBox";
            this.FristNumtextBox.Size = new System.Drawing.Size(82, 20);
            this.FristNumtextBox.TabIndex = 1;
            // 
            // SecondNumtextBox
            // 
            this.SecondNumtextBox.Location = new System.Drawing.Point(133, 38);
            this.SecondNumtextBox.Name = "SecondNumtextBox";
            this.SecondNumtextBox.Size = new System.Drawing.Size(82, 20);
            this.SecondNumtextBox.TabIndex = 2;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(18, 12);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(93, 13);
            this.label1.TabIndex = 3;
            this.label1.Text = "Choose the action";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(32, 67);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(13, 13);
            this.label2.TabIndex = 4;
            this.label2.Text = "=";
            // 
            // ResultTextBox
            // 
            this.ResultTextBox.Location = new System.Drawing.Point(51, 64);
            this.ResultTextBox.Name = "ResultTextBox";
            this.ResultTextBox.Size = new System.Drawing.Size(164, 20);
            this.ResultTextBox.TabIndex = 5;
            this.ResultTextBox.TextChanged += new System.EventHandler(this.textBox3_TextChanged);
            // 
            // historyLlistBox
            // 
            this.historyLlistBox.FormattingEnabled = true;
            this.historyLlistBox.Location = new System.Drawing.Point(12, 123);
            this.historyLlistBox.Name = "historyLlistBox";
            this.historyLlistBox.Size = new System.Drawing.Size(203, 30);
            this.historyLlistBox.TabIndex = 6;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 107);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(39, 13);
            this.label3.TabIndex = 7;
            this.label3.Text = "History";
            // 
            // computeButton
            // 
            this.computeButton.Location = new System.Drawing.Point(140, 7);
            this.computeButton.Name = "computeButton";
            this.computeButton.Size = new System.Drawing.Size(75, 23);
            this.computeButton.TabIndex = 8;
            this.computeButton.Text = "Compute";
            this.computeButton.UseVisualStyleBackColor = true;
            this.computeButton.Click += new System.EventHandler(this.computeButton_Click);
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(12, 163);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.Size = new System.Drawing.Size(240, 150);
            this.dataGridView1.TabIndex = 9;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(270, 325);
            this.Controls.Add(this.dataGridView1);
            this.Controls.Add(this.computeButton);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.historyLlistBox);
            this.Controls.Add(this.ResultTextBox);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.SecondNumtextBox);
            this.Controls.Add(this.FristNumtextBox);
            this.Controls.Add(this.ActioncomboBox);
            this.Name = "Form1";
            this.Text = "Calculator";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox ActioncomboBox;
        private System.Windows.Forms.TextBox FristNumtextBox;
        private System.Windows.Forms.TextBox SecondNumtextBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox ResultTextBox;
        private System.Windows.Forms.ListBox historyLlistBox;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button computeButton;
        private System.Windows.Forms.DataGridView dataGridView1;
    }
}


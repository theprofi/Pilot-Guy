namespace GetPixelColor
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
            this.setTimeBTN = new System.Windows.Forms.Button();
            this.startBTN = new System.Windows.Forms.Button();
            this.InfoLBL = new System.Windows.Forms.Label();
            this.setTimeTXB = new System.Windows.Forms.TextBox();
            this.minutesLBL = new System.Windows.Forms.Label();
            this.restartBTN = new System.Windows.Forms.Button();
            this.timeRemainLBL = new System.Windows.Forms.Label();
            this.timeLeftLBL = new System.Windows.Forms.Label();
            this.hkInfoLBL = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // setTimeBTN
            // 
            this.setTimeBTN.Location = new System.Drawing.Point(269, 62);
            this.setTimeBTN.Name = "setTimeBTN";
            this.setTimeBTN.Size = new System.Drawing.Size(75, 23);
            this.setTimeBTN.TabIndex = 0;
            this.setTimeBTN.Text = "Set time";
            this.setTimeBTN.UseVisualStyleBackColor = true;
            this.setTimeBTN.Click += new System.EventHandler(this.button1_Click);
            // 
            // startBTN
            // 
            this.startBTN.Location = new System.Drawing.Point(113, 111);
            this.startBTN.Name = "startBTN";
            this.startBTN.Size = new System.Drawing.Size(75, 23);
            this.startBTN.TabIndex = 2;
            this.startBTN.Text = "Start";
            this.startBTN.UseVisualStyleBackColor = true;
            this.startBTN.Click += new System.EventHandler(this.button2_Click);
            // 
            // InfoLBL
            // 
            this.InfoLBL.AutoSize = true;
            this.InfoLBL.Location = new System.Drawing.Point(203, 17);
            this.InfoLBL.Name = "InfoLBL";
            this.InfoLBL.Size = new System.Drawing.Size(45, 13);
            this.InfoLBL.TabIndex = 3;
            this.InfoLBL.Text = "Info text";
            // 
            // setTimeTXB
            // 
            this.setTimeTXB.Location = new System.Drawing.Point(113, 64);
            this.setTimeTXB.Name = "setTimeTXB";
            this.setTimeTXB.Size = new System.Drawing.Size(64, 20);
            this.setTimeTXB.TabIndex = 4;
            // 
            // minutesLBL
            // 
            this.minutesLBL.AutoSize = true;
            this.minutesLBL.Location = new System.Drawing.Point(219, 67);
            this.minutesLBL.Name = "minutesLBL";
            this.minutesLBL.Size = new System.Drawing.Size(44, 13);
            this.minutesLBL.TabIndex = 5;
            this.minutesLBL.Text = "Minutes";
            // 
            // restartBTN
            // 
            this.restartBTN.Location = new System.Drawing.Point(194, 111);
            this.restartBTN.Name = "restartBTN";
            this.restartBTN.Size = new System.Drawing.Size(75, 23);
            this.restartBTN.TabIndex = 6;
            this.restartBTN.Text = "Restart";
            this.restartBTN.UseVisualStyleBackColor = true;
            // 
            // timeRemainLBL
            // 
            this.timeRemainLBL.AutoSize = true;
            this.timeRemainLBL.Location = new System.Drawing.Point(152, 155);
            this.timeRemainLBL.Name = "timeRemainLBL";
            this.timeRemainLBL.Size = new System.Drawing.Size(78, 13);
            this.timeRemainLBL.TabIndex = 7;
            this.timeRemainLBL.Text = "Time remaining";
            // 
            // timeLeftLBL
            // 
            this.timeLeftLBL.AutoSize = true;
            this.timeLeftLBL.Location = new System.Drawing.Point(166, 179);
            this.timeLeftLBL.Name = "timeLeftLBL";
            this.timeLeftLBL.Size = new System.Drawing.Size(47, 13);
            this.timeLeftLBL.TabIndex = 8;
            this.timeLeftLBL.Text = "Time left";
            // 
            // hkInfoLBL
            // 
            this.hkInfoLBL.AutoSize = true;
            this.hkInfoLBL.Location = new System.Drawing.Point(166, 206);
            this.hkInfoLBL.Name = "hkInfoLBL";
            this.hkInfoLBL.Size = new System.Drawing.Size(42, 13);
            this.hkInfoLBL.TabIndex = 9;
            this.hkInfoLBL.Text = "HK info";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(393, 238);
            this.Controls.Add(this.hkInfoLBL);
            this.Controls.Add(this.timeLeftLBL);
            this.Controls.Add(this.timeRemainLBL);
            this.Controls.Add(this.restartBTN);
            this.Controls.Add(this.minutesLBL);
            this.Controls.Add(this.setTimeTXB);
            this.Controls.Add(this.InfoLBL);
            this.Controls.Add(this.startBTN);
            this.Controls.Add(this.setTimeBTN);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button setTimeBTN;
        private System.Windows.Forms.Button startBTN;
        private System.Windows.Forms.Label InfoLBL;
        private System.Windows.Forms.TextBox setTimeTXB;
        private System.Windows.Forms.Label minutesLBL;
        private System.Windows.Forms.Button restartBTN;
        private System.Windows.Forms.Label timeRemainLBL;
        private System.Windows.Forms.Label timeLeftLBL;
        private System.Windows.Forms.Label hkInfoLBL;
    }
}


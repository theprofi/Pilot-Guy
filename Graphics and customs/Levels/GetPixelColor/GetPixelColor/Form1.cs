using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GetPixelColor
{
    public partial class Form1 : Form
    {
        const int WIDTH = 663;
        const int HEIGHT = 340;
        int ma = 0;
        int addCount = 1,obCounter = 0;
        bool[,] arr = new bool[WIDTH, HEIGHT];
        public Form1()
        {
            InitializeComponent();
           
        }

        private void button1_Click(object sender, EventArgs e)
        {
            GetPixel();
            textBox1.Text += " private void Add1() {\r\n";
            for (int i = 0; i < WIDTH; i++)
                for (int j = 0; j < HEIGHT; j++)
                {
                    if (arr[i, j])
                    {
                        textBox1.Text += ("borderLine[" + (HEIGHT - 1 - j) + "] [" + i + "] = true;\r\n");
                        obCounter++;
                        if (obCounter > 1200)
                        {
                            addCount++;
                            textBox1.Text += "\r\n}\r\n";
                            textBox1.Text += "\r\nprivate void Add" + addCount + "() {\r\n";
                            obCounter = 0;
                        }
                    }
                }
           // MessageBox.Show(ma.ToString());
            textBox1.Text += "\r\n}\r\n";
        }

        private void GetPixel()
        {
            Bitmap bmp = (Bitmap)Image.FromFile("e:\\toScan.png");
            LockBitmap lockBitmap = new LockBitmap(bmp);
            lockBitmap.LockBits();

            Color compareClr = Color.FromArgb(0, 0, 0, 0);
            for (int y = 0; y < HEIGHT; y++) //YYYYYY!!
            {
                for (int x = 0; x < WIDTH; x++) //XXXXXX
                {
                    if (lockBitmap.GetPixel(x, y).R == 127 /*127*/ && lockBitmap.GetPixel(x, y).G == 127 /*38*/ && lockBitmap.GetPixel(x, y).B == 127 /*45*/)
                    {
                        arr[x, y] = true;
                        ma++;
                    }
                }
            }
            lockBitmap.UnlockBits();

            /*
            // Create a Bitmap object from an image file.
            Bitmap myBitmap = new Bitmap("E:\\lvl1_2.png");

            // Get the color of a pixel within myBitmap.
            return myBitmap.GetPixel(x, y).A;*/
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Clipboard.SetText(textBox1.Text);
        }
    }
}

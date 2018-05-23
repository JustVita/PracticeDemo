using Android.App;
using Android.Widget;
using Android.OS;
using ZXing.Mobile;
using System;
using ZXing;

namespace ZxingScanner
{
    [Activity(Label = "ZxingScanner", MainLauncher = true, Theme = "@android:style/Theme.DeviceDefault.Light")]
    public class MainActivity : Activity
    {
        TextView tv;
        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);

            // Set our view from the "main" layout resource
            SetContentView(Resource.Layout.Main);

            MobileBarcodeScanner.Initialize(Application);

            var button = FindViewById<Button>(Resource.Id.btn);
            tv = FindViewById<TextView>(Resource.Id.tv);


            button.Click += async delegate
            {
                var scanner = new ZXing.Mobile.MobileBarcodeScanner();
                var result = await scanner.Scan();

                if(result == null)
                {
                    return;
                }
                else
                {
                    if (!string.IsNullOrEmpty(result.Text))
                    {
                        ScanResultHandler(result);
                    }
                }

            };

        }

        private void ScanResultHandler(ZXing.Result result)
        {
            string url = result.Text;
            tv.Text = string.IsNullOrEmpty(url) ? "扫描取消" : url;
        }
    }
}


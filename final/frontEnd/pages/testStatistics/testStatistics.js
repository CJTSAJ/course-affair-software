var wxCharts = require('../../utils/wxcharts.js');
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    success: [],
    fail: [],
    statistics: [0, 0, 0, 0, 0, 0],
    width: 360,
    height: 280
  },

  onLoad: function(options){
    var self = this;
    wx.getSystemInfo({
      success: function (res) {
        console.log(res.windowWidth);
        console.log(res.windowHeight);
        self.setData({
          width: res.windowWidth,
          height: res.windowHeight/2
        })
      }
    })
    var id = options.testid;
    console.log(id);
    wx.request({
      url: app.globalData.serverUrl + 'getStudentGrade',
      data: {
        testid: id,
        opengid: app.globalData.openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        console.log(res.data);
        if(res.statusCode == 200){
          var fail = res.data.fail;
          if(res.data.success == "none"){
            var temp = [fail.length, 0, 0, 0, 0, 0]
            self.setData({
              success: [],
              fail: fail,
              statistics: temp
            })
          }else{
            var down6 = 0, up6 = 0, up7 = 0, up8 = 0, up9 = 0;

            for(var i = 0; i < res.data.success.length; i++){
              var tempG = fail[i]
              if(tempG < 60){
                down6++;
              }else if(tempG < 70){
                up6++;
              }else if(tempG < 80){
                up7++;
              }else if(tempG < 90){
                up8++;
              }else{
                up9++;
              }
            }

            var allData = [fail.length, down6, up6, up7, up8, up9]
            self.setData({
              success: res.data.success,
              fail: res.data.fail,
              statistics: allData
            })
          }
          self.showChart();
        }else{
          wx.showModal({
            title: '错误',
            content: '服务器错误',
          })
        }
      },
      fail: function(res){
        wx.showModal({
          title: '错误',
          content: res,
          showCancel: false
        })
      }
    })
  },
  showChart: function(){
    var self = this
    new wxCharts({
      canvasId: 'columnCanvas',
      type: 'column',
      animation: true,
      categories: ['未完成', '60以下', '60-70', '70-80', '80-90', '90-100'],
      series: [{
        name: '人数',
        data: self.data.statistics,
        /*format: function (val, name) {
          return val.toFixed(2) + '万';
        }*/
      }],
      yAxis: {
        /*format: function (val) {
          return val + '万';
        },*/
        title: '',
        min: 0
      },
      xAxis: {
        disableGrid: false,
        type: 'calibration'
      },
      extra: {
        column: {
          width: 15
        }
      },
      width: self.data.width,
      height: self.data.height,
    });
  }
})
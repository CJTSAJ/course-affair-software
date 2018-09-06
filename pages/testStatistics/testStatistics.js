var wxCharts = require('../../utils/wxcharts.js');
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  onLoad: function(options){
    var id = options.testid;
    console.log(id);
    wx.request({
      url: app.globalData.serverUrl + 'getStudentGrade',
      data: {
        testid: parseInt(id),
        opengid: app.globalData.openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        console.log(res.data);
      },
      fail: function(res){
        wx.showModal({
          title: '错误',
          content: res,
          showCancel: false
        })
      }
    })
    wx.getSystemInfo({
      success:function(res){
        console.log(res.windowWidth);
        console.log(res.windowHeight);
      }
    })
    new wxCharts({
      canvasId: 'columnCanvas',
      type: 'column',
      animation: true,
      categories: ['未完成', '60以下', '60-70', '70-80', '80-90', '90-100'],
      series: [{
        name: '人数',
        data: [25, 10, 20, 30, 40, 35],
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
      width: 375,
      height: 250,
    });
  }
})
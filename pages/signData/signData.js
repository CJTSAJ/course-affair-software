// pages/signData/signData.js
var wxCharts = require('../../utils/wxcharts.js');
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    successSign: [],
    failSign: []
  },

  onLoad:function(options){
    var self = this;
    var id = options.id;
    wx.request({
      url: app.globalData.serverUrl + 'getSignRecord',
      data:{
        id: id,
        opengid: app.globalData.openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        console.log(res.data.success);
        console.log(res.data.fail);
        self.setData({
          successSign: res.data.success,
          failSign: res.data.fail
        })
        new wxCharts({
          canvasId: 'columnCanvas',
          type: 'column',
          animation: true,
          categories: ['已签到', '未签到'],
          series: [{
            name: '人数',
            data: [res.data.success.length, res.data.fail.length],
          }],
          yAxis: {
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
  }
})
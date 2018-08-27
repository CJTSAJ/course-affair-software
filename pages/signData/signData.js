// pages/signData/signData.js
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
      url: 'http://localhost:8080/getSignRecord',
      data:{
        id: id
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
      }
    })
  }
})
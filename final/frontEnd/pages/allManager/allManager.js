const app = getApp()
// pages/allManager/allManager.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    size: 0,
    allTa:[],
    allTeacher: []
  },
  onShow:function(){
    wx.showLoading({
      title: '加载中',
    })
    console.log("allManager onshow")
    var self = this;
    wx.request({
      url: app.globalData.serverUrl + 'getTeacherAndTa',
      data: {
        opengid: app.globalData.openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        console.log(res.data.ta[0])
        self.setData({
          allTa: res.data.ta,
          allTeacher: res.data.teacher,
          size: res.data.ta.length
        })
        wx.hideLoading()
      }
    })
  },
  deleteTa:function(e){
    var self = this;
    var index = e.target.dataset.id;
    console.log(index);
    wx.showModal({
      title: '提示',
      content: '确定取消该助教身份',
      success: function (res) {
        if (res.confirm) {
          var taGroupId = self.data.allTa[index].taGroupId
          var taid = self.data.allTa[index].taid
          var newArr = self.data.allTa
          newArr.splice(index, 1);
          self.setData({
            allTa: newArr,
            size: newArr.length
          })

          wx.request({
            url: app.globalData.serverUrl + 'deleteTa',
            data: {
              opengid: taGroupId,
              openid: taid
            },
            method: 'POST',
            header: { 'content-type': 'application/json' },
            success:function(){
              console.log("删除管理员成功")
            }
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },
  addManger:function(){
    var self = this;
    var model = JSON.stringify(self.data.allTa);
    wx.navigateTo({
      url: '/pages/managerSetting/mangerSetting?allTa=' + model,
    })
  }
})
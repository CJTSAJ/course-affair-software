
const app = getApp()
// pages/managerSetting/mangerSetting.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    allMember: [],
    selectedMember: [],
    allTa: []
  },
  onShow:function(){
    wx.showLoading({
      title: '加载中',
    })
    /*向后端获取该群内所有学生名单*/
    var self = this;
     wx.request({
       url: 'http://207.148.114.118:8080/courseAffair/getStudentByGid',
       data:{
         opengid: app.globalData.openGId
       },
       method: 'POST',
       header: { 'content-type': 'application/json' },
       success:function(res){
         self.setData({
           allMember: res.data
         })
         wx.hideLoading()
       }
     })
  },

  onLoad: function(options){
    var bean = JSON.parse(options.allTa);
    console.log(bean.length)
    this.setData({
      allTa: bean
    })
  },

  confirm:function(){
    //向后端发送数据
    var selected = this.data.selectedMember;
    console.log(this.data.allTa.length)
    console.log(selected.length);
    if((this.data.allTa.length + selected.length) > 15){
      wx.showModal({
        title: '提示',
        content: '管理员总人数不超过15个',
      })
    }else{
      var all = this.data.allMember;
      var openidArray = [];
      for (var i = 0; i < selected.length; i++) {
        openidArray.push(all[selected[i]].openid);
      }
      console.log(openidArray);

      wx.request({
        url: 'http://207.148.114.118:8080/courseAffair/addManager',
        data: {
          opengid: app.globalData.openGId,
          openidArray: openidArray
        },
        method: 'POST',
        header: { 'content-type': 'application/json' },
        success: function (res) {
          wx.navigateBack({
            delta: 1
          })
        }
      })
    }
    
  },
  checkboxChange:function(e){
    var selected = e.detail.value;
    console.log(selected)
    this.setData({
      selectedMember: selected
    })
  }
})
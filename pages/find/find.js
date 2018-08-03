const app = getApp()
Page({
  data: {
    isTeacher: false,
    signCode: null
  },
  toPerson:function(){
    wx.navigateTo({
      url: '/pages/person/person',
    })
  },
  
  toFile:function(){
    wx.navigateTo({
      url: '/pages/uploadFile/uploadFile',
    })
  },
  toNotice:function(){
    wx.navigateTo({
      url: '/pages/allNotice/allNotice',
    })
  },
  toHomework:function(){
    wx.navigateTo({
      url: '/pages/allHomework/allHomework',
    })
  },
  toManage:function(){
    wx.navigateTo({
      url: '/pages/manage/manage',
    })
  },

  toSign:function(){
    wx.navigateTo({
      url: '/pages/sign/sign',
    })
  },

  toTest:function(){
    wx.navigateTo({
      url: '/pages/test/test',
    })
  },
  
  onShow: function(){
    
    if(app.globalData.identity == "teacher"){
      this.setData({
        isTeacher: true
      })
    }
  }
  /*sign:function(){
    this.setData({
      showModal: true
    })
  },
  hideModal:function(){
    this.setData({
      showModal: false
    })
  },
  //取消按钮
  cancel: function () {
    this.setData({
      showModal: false
    });
  },
  //确认
  confirm: function () {
    this.setData({
      showModal: false
    })
  },
  onConfirm:function(){
    this.hideModal();
    wx.request({
      url: '',
      data: this.data.signCode,
      method: 'POST',
      success: function (res) {
        console.log('submit success');
      },
      fail: function (res) {
        console.log('submit fail');
      },
      complete: function (res) {
        console.log('submit complete');
      }

    })
  },
  onCancel:function(){
    this.hideModal();
    this.setData({
      signCode: 0
    })
  },
  inputChange:function(e){
    this.setData({
      signCode: e.detail.value
    })
  },
  toManage:function(){
    wx.navigateTo({
      url: '/pages/manage/manage',
    })
  },
  modalinput: function () {
    this.setData({
      hiddenmodalput: !this.data.hiddenmodalput
    })
  },
  */
})
// pages/editVoteContent/editVoteContent.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    voteContent: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
    var voteContent = prevPage.data.voteContent;
    this.setData({
      voteContent: voteContent,
    })
    console.log(this.data);
  },

  inputVoteContent: function (e) {
    console.log(e);
    this.data.voteContent = e.detail.value;
    this.setData({
      voteContent: this.data.voteContent
    })
  },

  confirm: function () {
    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
    prevPage.data.voteContent = this.data.voteContent;
    prevPage.setData({
      voteContent: prevPage.data.voteContent
    });
    wx.navigateBack({
      changed: true
    });
  },

  cansel: function () {
    wx.navigateBack({
      changed: true
    });
  },

})
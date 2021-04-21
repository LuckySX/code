module.exports = {
  base: '/',
  title: 'Docs',
  port: '4444',
  markdown: {
    lineNumber: false //
  },
  themeConfig: {
    nav: [
      // 添加导航栏
      {
        text: '首页',
        link: '/'
      },
      {
        text: '环境',
        link: '/initialization/'
      },
      {
        text: '使用',
        link: '/use/'
      },
      {
        text: '技术栈',
        link: '/info/'
      },
      {
        text: '规范',
        link: '/grammer',
        items: [
          { text: '/ts', link: '/grammar/typescript.md' },
          { text: '/css', link: '/grammar/css.md' },
          { text: '/axios', link: '/grammar/axios.md' }
        ]
      }
    ],
    // 添加侧边栏
    sidebar: 'auto',
    sidebarDepth: 2
  },
  plugins: ['@vuepress/back-to-top']
}

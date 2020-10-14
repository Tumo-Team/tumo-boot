import request from '@/utils/request'

/**
 * 分页、条件查询
 */
export function articleList(pagination, query) {
  return request({
    url: `/article/list?page=${pagination.page}&size=${pagination.size}`,
    method: 'post',
    query
  })
}

/**
 * 条件查询
 */
export function articleFilterList(data) {
  return request({
    url: `/article/filter/list`,
    method: 'post',
    data
  })
}

/**
 * 根据ID查询
 */
export function findByArticleId(id) {
  return request({
    url: `/article/${id}`,
    method: 'get'
  })
}

/**
 * 新增
 */
export function addArticle(data) {
  return request({
    url: `/article`,
    method: 'post',
    data
  })
}

/**
 * 修改
 */
export function updateArticle(data) {
  return request({
    url: `/article`,
    method: 'put',
    data
  })
}

/**
 * 删除
 */
export function deleteArticle(id) {
  return request({
    url: `/article/${id}`,
    method: 'delete'
  })
}

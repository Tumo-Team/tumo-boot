#!/usr/bin/env sh

# 确保脚本抛出遇到的错误
set -e

# 先删除.git仓库
rm -rf .git

git init
git add -A
git commit -m 'deploy'

git push -f "root@tycoding.cn:/var/www/tumo-boot-docs.git" master

# 推送完成后删除git仓库
rm -rf .git

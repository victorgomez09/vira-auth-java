import { TreeNode } from "primeng/api"
import { Page } from "../models/docs.model"

export const parsePageToTreeNode = (page: Page): TreeNode => {
    if (page.parent) {
        console.log('i have parent', page.name)
    }
    return {
        key: String(page.treePos),
        label: page.name,
        data: page.id,
        leaf: false,
        parent: page.parent ? parsePageToTreeNode(page.parent) : undefined
    }
}
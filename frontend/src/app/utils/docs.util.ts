import { TreeNode } from "primeng/api"
import { Page } from "../models/docs.model"

export const parsePageToTreeNode = (page: Page): TreeNode => {
    return {
        key: String(page.treePos),
        label: page.name,
        data: page.body,
    }
}
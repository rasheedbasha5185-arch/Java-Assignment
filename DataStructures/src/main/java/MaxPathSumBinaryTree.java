class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

class MaxPathSumBinaryTree {
    static int maxSum;

    public static int findMaxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        calculatePathSum(root);
        return maxSum;
    }

    private static int calculatePathSum(TreeNode node) {
        if (node == null) return 0;

        // Calculate maximum path sum for left and right child
        int leftSum = Math.max(0, calculatePathSum(node.left));
        int rightSum = Math.max(0, calculatePathSum(node.right));

        // Update the maxSum with the path passing through this node
        maxSum = Math.max(maxSum, leftSum + rightSum + node.val);

        // Return the max path including this node to its parent
        return Math.max(leftSum, rightSum) + node.val;
    }

    public static void main(String[] args) {
        /*
               10
              /  \
             2    10
            / \     \
           20  1     -25
                     /  \
                    3    4
        */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(2);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(-25);
        root.right.right.left = new TreeNode(3);
        root.right.right.right = new TreeNode(4);

        System.out.println("Maximum Path Sum = " + findMaxPathSum(root));
    }
}

#include <stdio.h> 

#include <stdlib.h> 

  

typedef struct TreeNode { 

    int frequency; 

    int pixelValue; // Pixel intensity value for leaf nodes, -1 for internal nodes 

    struct TreeNode* left; 

    struct TreeNode* right; 

} TreeNode; 

  

TreeNode* createNode(int frequency, int pixelValue) { 

    TreeNode* newNode = (TreeNode*)malloc(sizeof(TreeNode)); 

    newNode->frequency = frequency; 

    newNode->pixelValue = pixelValue; 

    newNode->left = NULL; 

    newNode->right = NULL; 

    return newNode; 

} 

  

void swap(TreeNode** a, TreeNode** b) { 

    TreeNode* temp = *a; 

    *a = *b; 

    *b = temp; 

} 

  

void sortNodes(TreeNode** nodes, int size) { 

    for (int i = 0; i < size - 1; i++) { 

        for (int j = i + 1; j < size; j++) { 

            if (nodes[i]->frequency > nodes[j]->frequency) { 

                swap(&nodes[i], &nodes[j]); 

            } 

        } 

    } 

} 

  

TreeNode* buildHuffmanTree(int* frequencies, int* pixelValues, int size) { 

    TreeNode* nodes[size]; 

    for (int i = 0; i < size; i++) { 

        nodes[i] = createNode(frequencies[i], pixelValues[i]); 

    } 

  

    while (size > 1) { 

        sortNodes(nodes, size); 

  

        TreeNode* left = nodes[0]; 

        TreeNode* right = nodes[1]; 

  

        TreeNode* z = createNode(left->frequency + right->frequency, -1); 

        z->left = left; 

        z->right = right; 

  

        nodes[1] = z; 

        for (int i = 0; i < size - 1; i++) { 

            nodes[i] = nodes[i + 1]; 

        } 

        size--; 

    } 

  

    return nodes[0]; 

} 

  

void printCodes(TreeNode* root, int arr[], int top, int* values, int* freq, int* codewords, int* sizes, int* idx) { 

    if (root->left) { 

        arr[top] = 1; 

        printCodes(root->left, arr, top + 1, values, freq, codewords, sizes, idx); 

    } 

  

    if (root->right) { 

        arr[top] = 0; 

        printCodes(root->right, arr, top + 1, values, freq, codewords, sizes, idx); 

    } 

  

    if (!(root->left) && !(root->right)) { 

        printf("Pixel Intensity Value: %d, Code: ", root->pixelValue); 

        for (int i = 0; i < top; ++i) { 

            printf("%d", arr[i]); 

        } 

        printf("\n"); 

         

        values[*idx] = root->pixelValue; 

        freq[*idx] = root->frequency; 

        sizes[*idx] = top; 

        for (int i = 0; i < top; ++i) { 

            codewords[*idx] = (codewords[*idx] << 1) | arr[i]; 

        } 

        (*idx)++; 

    } 

} 

  

void printHuffmanCodes(TreeNode* root, int* image, int imageSize) { 

    int arr[100]; 

    int top = 0; 

    int values[100]; 

    int freq[100]; 

    int codewords[100] = {0}; 

    int sizes[100]; 

    int idx = 0; 

     

    printf("\nHuffman Codes:\n"); 

    printCodes(root, arr, top, values, freq, codewords, sizes, &idx); 

  

    for (int i = 0; i < idx - 1; ++i) { 

        for (int j = i + 1; j < idx; ++j) { 

            if (freq[i] < freq[j]) { 

                int tempVal = values[i]; 

                int tempFreq = freq[i]; 

                int tempCode = codewords[i]; 

                int tempSize = sizes[i]; 

  

                values[i] = values[j]; 

                freq[i] = freq[j]; 

                codewords[i] = codewords[j]; 

                sizes[i] = sizes[j]; 

  

                values[j] = tempVal; 

                freq[j] = tempFreq; 

                codewords[j] = tempCode; 

                sizes[j] = tempSize; 

            } 

        } 

    } 

  

    printf("\nHuffman Codes and Sizes:\n"); 

    printf("Pixel Intensity Value\tFrequency\tCode-word\tSize\n"); 

    for (int i = 0; i < idx; ++i) { 

        printf("%d\t\t\t%d\t\t\t", values[i], freq[i]); 

        for (int j = sizes[i] - 1; j >= 0; --j) { 

            printf("%d", (codewords[i] >> j) & 1); 

        } 

        printf("\t\t\t%d*%d\n", freq[i], sizes[i]); 

    } 

  

    int totalSize = 0; 

    for (int i = 0; i < idx; ++i) { 

        totalSize += freq[i] * sizes[i]; 

    } 

    printf("\nTotal Size: %d\n", totalSize); 

  

    printf("\nCompressed Image Bitstream:\n"); 

    for (int i = 0; i < imageSize; i++) { 

        for (int j = 0; j < idx; j++) { 

            if (image[i] == values[j]) { 

                for (int k = sizes[j] - 1; k >= 0; k--) { 

                    printf("%d", (codewords[j] >> k) & 1); 

                } 

                break; 

            } 

        } 

    } 

    printf("\n"); 

} 

  

void calculateFrequency(int* image, int* freq, int* values, int imageSize, int* uniqueCount) { 

    for (int i = 0; i < imageSize; i++) { 

        int found = 0; 

        for (int j = 0; j < *uniqueCount; j++) { 

            if (values[j] == image[i]) { 

                freq[j]++; 

                found = 1; 

                break; 

            } 

        } 

        if (!found) { 

            values[*uniqueCount] = image[i]; 

            freq[*uniqueCount] = 1; 

            (*uniqueCount)++; 

        } 

    } 

  

    printf("\nTable-1: Pixel Intensity Value and Frequency\n"); 

    printf("Pixel Intensity Value\tFrequency\n"); 

    for (int i = *uniqueCount - 1; i >= 0; i--) { // Print in reverse order 

        printf("%d\t\t\t%d\n", values[i], freq[i]); 

    } 

} 

  

void compressImage(int* image, int imageSize) { 

    int freq[256] = {0}; 

    int values[256] = {0}; 

    int uniqueCount = 0; 

  

    calculateFrequency(image, freq, values, imageSize, &uniqueCount); 

  

    TreeNode* root = buildHuffmanTree(freq, values, uniqueCount); 

  

    printf("\nHuffman Tree built successfully!\n"); 

    printHuffmanCodes(root, image, imageSize); 

} 

  

int main() { 

    int choice; 

  

    while (1) { 

        printf("\nMenu:\n"); 

        printf("1. Compress Image\n"); 

        printf("2. Exit\n"); 

        printf("Enter your choice: "); 

        scanf("%d", &choice); 

  

        if (choice == 1) { 

             

            int image[100], imageSize; 

  

            printf("Enter the number of pixel intensity values in the image: "); 

            scanf("%d", &imageSize); 

  

            printf("Enter the pixel intensity values:\n"); 

            for (int i = 0; i < imageSize; i++) { 

            scanf("%d", &image[i]); 

            } 

            compressImage(image, imageSize); 

        } else if (choice == 2) { 

            break; 

        } else { 

            printf("Invalid choice. Please try again.\n"); 

        } 

    } 

  

    return 0; 

} 
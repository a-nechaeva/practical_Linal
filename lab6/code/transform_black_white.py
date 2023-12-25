from PIL import Image
import matplotlib.image as mpimg
import numpy as np
import matplotlib.pyplot as plt


img = Image.open('red_eye.png')
black_and_white = img.convert('L')
black_and_white.save('bw_red.png')

matrix_image = mpimg.imread('bw_red.png')

U, s, W = np.linalg.svd(matrix_image)
s = np.diag(s)

k = [20000, 4500, 1000, 250, 100, 50, 20, 10, 5, 1]

for i in k:
    cut = np.dot(np.dot(U[:, :i], s[:i, :i]), W[:i, :])
    orig_size = np.shape(U)[0] * np.shape(U)[1] + np.shape(s)[0] + np.shape(W)[0] * np.shape(W)[1]
    cut_size = np.shape(U[:, :i])[0] * np.shape(U[:, :i])[1] + np.shape(s[0:i, :i])[0] + np.shape(W[:i, :])[0] * np.shape(W[:i, :])[1]
    print(100 * (1 - cut_size / orig_size))
    print('---------------')
    print('\n')

    fig, axs = plt.subplots(1, 2)
    axs[0].axis('off')
    axs[1].axis('off')

    axs[0].imshow(matrix_image).set_cmap('grey')
    axs[1].imshow(cut).set_cmap('grey')

    axs[0].set_title('Оригинал')
    axs[1].set_title('После сжатия, k=' + str(i))
    plt.show()


 #mpimg.imsave('after_svd.png', np.dot(np.dot(U, D), V.T))

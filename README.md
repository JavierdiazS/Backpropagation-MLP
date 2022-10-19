# Backpropagation-MLP

# Introduction

With the backpropagation of the neural network, It's used for yellow color detection. Completely programmed in Java.

# Diagram of the perceptron used
![1](https://user-images.githubusercontent.com/75210642/196562346-82d79c73-019e-455f-bb04-b05244eb9219.png)
> multilayer perceptron diagram

The multilayer perceptron diagram we use 3 inputs, for the color RED, GREEN, BLUE, respectively. Use 1 hidden layer of 4 neurons in test mode, according to the training, it's evident that with only 4 neurons in the hidden layer it was enough, and 1 single output which indicates that if it's a negative value, for example, -0.995 won't correspond to the color of the tape yellow and therefore the pixel will be assigned a gray color (127/127/127), if it's a positive value for example, 0.995 will correspond to the color of the yellow tape and the pixel will be assigned a white color (255 /255/255).

# Used Examples
![2](https://user-images.githubusercontent.com/75210642/196564201-e88f1b15-7b77-4448-9429-6ef27bcbc20b.png)
> Examples divided by percentages (Ordered).

![3](https://user-images.githubusercontent.com/75210642/196564345-3e29cce1-d9a2-4ed8-b783-461512b3691a.png)
> Examples used to train (Unordered).

The examples we used were a total of 60. They were taken manually with Paint 80% being the values of each pixel corresponding to those of the track and those of the environment. For the other 20% it's the values of the yellow tape, the different yellow colors.
The training examples to be used in the training will be the same, only they'll be mixed.

# Results Obtained

![4](https://user-images.githubusercontent.com/75210642/196565732-1f4b622c-0c70-49a1-a199-a5fe9bd876c4.png)
>Image reading with the multilayer perceptron.

![5](https://user-images.githubusercontent.com/75210642/196565741-e6e3258d-5ee4-450d-85d1-2906d69f5a85.png)
>JK and IJ weights and thresholds.

![6](https://user-images.githubusercontent.com/75210642/196565747-26eb0ea8-893f-4fcb-8e92-1ea21901a283.png)
>Output displayed by the console.

After having performed the training of the neural network, I mean, having calculated the activation potential hi and hj, and the calculation of the activation function (hyperbolic tangent) yi and yj, the variations, the ECM, the values of the weights and thresholds JK and IJ. It is found that with only 55000 iterations, an epsilon value of 0.5, a lambda value of 0.03, an ECM of 0.04 and output values of 0.95 approximated to 1 in the outputs. The training has been a success since the signs of the response data correspond to the desired responses. We also obtain the values of the weights and thresholds JK and IJ. Then it will be saved in Image format to be saved in a folder and go overwriting the image in each taking of frames or photos layer a certain time, using it to be displayed in the interface.

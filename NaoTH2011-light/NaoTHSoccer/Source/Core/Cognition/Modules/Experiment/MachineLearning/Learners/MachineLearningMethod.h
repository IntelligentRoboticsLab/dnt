#ifndef MACHINELEARNINGMETHOD_H
#define MACHINELEARNINGMETHOD_H

#include <string>

class MachineLearningMethod
{
public:
    virtual void update(double fitness) = 0;
    virtual bool isFinished() const = 0;
    std::string name;
};

#endif // MACHINELEARNINGMETHOD_H

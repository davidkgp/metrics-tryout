## Docker challenges ##
since host.docker.internal was only available for Windows and Mac,
that's why the work around script `compose.sh` had to be used for running the container and setting the host ip for prometheus to scrape.

[Issue Description](https://github.com/docker/for-linux/issues/264)
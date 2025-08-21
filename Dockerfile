docker-push:
  runs-on: ubuntu-latest
  needs: trivy-scan

  steps:
    - name: Checkout code
      uses: actions/checkout@v3

    - name: Set up Docker Buildx
      uses: docker/setup-buildx-action@v3

    - name: Log in to GHCR
      uses: docker/login-action@v2
      with:
        registry: ghcr.io
        username: ${{ github.actor }}
        password: ${{ secrets.CR_PAT }}

    - name: Build Docker Image
      run: |
        docker build \
          --file Dockerfile \
          --tag ghcr.io/${{ github.repository_owner }}/devsonarproject:latest \
          .

    - name: Push Docker Image
      run: docker push ghcr.io/${{ github.repository_owner }}/devsonarproject:latest
